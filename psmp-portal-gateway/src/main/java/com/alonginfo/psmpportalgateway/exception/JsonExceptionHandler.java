package com.alonginfo.psmpportalgateway.exception;

import com.alonginfo.psmpcore.exception.AlongException;
import com.alonginfo.psmpcore.response.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rayjp
 * @create 2019-01-07
 **/
@Component
public class JsonExceptionHandler implements ErrorWebExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(JsonExceptionHandler.class);

  /**
   * MessageReader
   */
  private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

  /**
   * MessageWriter
   */
  private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();

  /**
   * ViewResolvers
   */
  private List<ViewResolver> viewResolvers = Collections.emptyList();

  /**
   * 存储处理异常后的信息
   */
  private ThreadLocal<Map<String,Object>> exceptionHandlerResult = new ThreadLocal<>();

  /**
   * 参考AbstractErrorWebExceptionHandler
   * @param messageReaders
   */
  public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
    Assert.notNull(messageReaders, "'messageReaders' must not be null");
    this.messageReaders = messageReaders;
  }

  /**
   * 参考AbstractErrorWebExceptionHandler
   * @param viewResolvers
   */
  public void setViewResolvers(List<ViewResolver> viewResolvers) {
    this.viewResolvers = viewResolvers;
  }

  /**
   * 参考AbstractErrorWebExceptionHandler
   * @param messageWriters
   */
  public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
    Assert.notNull(messageWriters, "'messageWriters' must not be null");
    this.messageWriters = messageWriters;
  }

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    /**
     * 按照异常类型进行处理
     */
    HttpStatus httpStatus;
    ResponseJson body;
    if (ex instanceof ResponseStatusException){
      ResponseStatusException responseStatusException = (ResponseStatusException) ex;
      httpStatus = responseStatusException.getStatus();
      body = new ResponseJson(httpStatus.value(),ex.getMessage());
    }else if(ex instanceof AlongException) {
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      body = new ResponseJson(((AlongException) ex).getCode(),ex.getMessage());
    }else{
      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      body = new ResponseJson(httpStatus.value(),ex.getMessage());
    }

    /**
     * 封装响应体,此body可修改为自己的jsonBody
     */
    Map<String,Object> result = new HashMap<>(2,1);
    result.put("httpStatus",httpStatus);
    result.put("body",body);
    /**
     * 错误记录
     */
    ServerHttpRequest request = exchange.getRequest();
    log.error("[全局异常处理]异常请求路径:{},记录异常信息:{}",request.getPath(),ex.getMessage());
    /**
     * 参考AbstractErrorWebExceptionHandler
     */
    if (exchange.getResponse().isCommitted()) {
      return Mono.error(ex);
    }
    exceptionHandlerResult.set(result);
    ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
    return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
        .switchIfEmpty(Mono.error(ex))
        .flatMap((handler) -> handler.handle(newRequest))
        .flatMap((response) -> write(exchange, response));

  }

  /**
   * 参考DefaultErrorWebExceptionHandler
   * @param request
   * @return
   */
  protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
    Map<String,Object> result = exceptionHandlerResult.get();
    return ServerResponse.status((HttpStatus) result.get("httpStatus"))
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(result.get("body")));
  }

  /**
   * 参考AbstractErrorWebExceptionHandler
   * @param exchange
   * @param response
   * @return
   */
  private Mono<? extends Void> write(ServerWebExchange exchange,
                                     ServerResponse response) {
    exchange.getResponse().getHeaders()
        .setContentType(response.headers().getContentType());
    return response.writeTo(exchange, new ResponseContext());
  }

  /**
   * 参考AbstractErrorWebExceptionHandler
   */
  private class ResponseContext implements ServerResponse.Context {

    @Override
    public List<HttpMessageWriter<?>> messageWriters() {
      return JsonExceptionHandler.this.messageWriters;
    }

    @Override
    public List<ViewResolver> viewResolvers() {
      return JsonExceptionHandler.this.viewResolvers;
    }

  }

  /**
   * 自定义异常处理[@@]注册Bean时依赖的Bean，会从容器中直接获取，所以直接注入即可
   * @param viewResolversProvider
   * @param serverCodecConfigurer
   * @return
   */
  @Primary
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                           ServerCodecConfigurer serverCodecConfigurer) {

    JsonExceptionHandler jsonExceptionHandler = new JsonExceptionHandler();
    jsonExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
    jsonExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
    jsonExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
    log.debug("Init Json Exception Handler Instead Default ErrorWebExceptionHandler Success");
    return jsonExceptionHandler;
  }

}
