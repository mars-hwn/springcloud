package com.alonginfo.psmpportalgateway.filter;

import com.alonginfo.psmpcore.exception.AlongException;
import com.alonginfo.psmpcore.response.ResponseCode;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author rayjp
 * @create 2018-12-14
 **/
@Component
@Slf4j
public class SignatureFilter implements GlobalFilter,Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		boolean flag;
		ServerHttpRequest serverHttpRequest = exchange.getRequest();
		StringBuffer sb = new StringBuffer();
		serverHttpRequest.getQueryParams().forEach((k,v)->{
			sb.append(k);
			sb.append("=");
			sb.append(v.get(0));
			sb.append("&");
		});
		String queryParam = sb.length() > 0 ? sb.deleteCharAt(sb.length() - 1).toString() : sb.toString();
		log.debug("请求url->" + serverHttpRequest.getURI());
		log.debug("query参数->" + queryParam);

		sb.setLength(0);
		HttpHeaders headerNames = serverHttpRequest.getHeaders();
		headerNames.forEach((k,v) -> {
			if(k.toLowerCase().startsWith("a-")){
				sb.append(k);
				sb.append(":");
				sb.append(v.get(0));
				sb.append(";");
			}
		});

		log.debug("自定义HEAD参数：" + sb.toString());
		String timestamp = serverHttpRequest.getQueryParams().getFirst("_");
		flag = CheckRequestUtil.checkTimestamp(timestamp);
		if(!flag){
			throw new AlongException(ResponseCode.PERMISSION_TIME_OUT);
		}
		String nonce = serverHttpRequest.getHeaders().getFirst("A-Nonce");
		flag = CheckRequestUtil.checkNonce(nonce);
		log.debug("nonce->"+nonce);
		if(!flag){
			throw new AlongException(ResponseCode.PERMISSION_NO_NONCE);
		}
		// 签名
		String signature = serverHttpRequest.getHeaders().getFirst("A-Signature");
		log.debug("clent-signature->"+signature);

		if("GET".equalsIgnoreCase(serverHttpRequest.getMethodValue())){
			flag = CheckRequestUtil.checkSignature(queryParam,signature);
			if(!flag){
				throw new AlongException(ResponseCode.PERMISSION_SIGN_ERROR);
			}
			return chain.filter(exchange);
		}else {
			String bodyStr = resolveBodyFromRequest(serverHttpRequest);
			if (StringUtils.isEmpty(bodyStr)) {
				bodyStr = "";
			}
			// 得到Post请求的请求参数后，做你想做的事
			log.debug("body参数->" + bodyStr);
			//下面的将请求体再次封装写回到request里，传到下一级，否则，由于请求体已被消费，后续的服务将取不到值
			URI uri = serverHttpRequest.getURI();
			ServerHttpRequest request = serverHttpRequest.mutate().uri(uri).build();
			DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
			Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

			request = new ServerHttpRequestDecorator(request) {
				@Override
				public Flux<DataBuffer> getBody() {
					return bodyFlux;
				}
			};

			flag = CheckRequestUtil.checkSignature(queryParam + bodyStr,signature);
			if(!flag){
				throw new AlongException(ResponseCode.PERMISSION_SIGN_ERROR);
			}
			//封装request，传给下一级
			return chain.filter(exchange.mutate().request(request).build());
		}
	}

	/**
	 * 从Flux<DataBuffer>中获取字符串的方法
	 * @return 请求体
	 */
	private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
		//获取请求体
		Flux<DataBuffer> body = serverHttpRequest.getBody();

		AtomicReference<String> bodyRef = new AtomicReference<>();
		body.subscribe(buffer -> {
			CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
			DataBufferUtils.release(buffer);
			bodyRef.set(charBuffer.toString());
		});
		//获取request body
		return bodyRef.get();
	}

	private DataBuffer stringBuffer(String value) {
		byte[] bytes = value.getBytes(StandardCharsets.UTF_8);

		NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
		DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
		buffer.write(bytes);
		return buffer;
	}


	@Override
	public int getOrder() {
		return 0;
	}
}
