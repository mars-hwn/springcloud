package com.alonginfo.psmpportalgateway.filter;

import com.alonginfo.psmpcore.util.MD5Util;
import com.alonginfo.psmpcore.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;


@Component
@Slf4j
public class CheckRequestUtil {

	//秘钥
	private static String SECTRT;
	//时间戳超时时间
	private static long TIMEOUT;
	//redis 工具类
	private static RedisUtil redisUtil;
	/**
	 * 从redis 中获取 对应 token 值
	 *
	 * A-Nonce 全局唯一 userId 标识  key UUID 值
	 *
	 * */
	public static boolean checkNonce(String nonce) {
		boolean result = false;
		if (StringUtils.isEmpty(nonce)) {
			return false;
		}
		String value = redisUtil.get(nonce);
		result = StringUtils.isEmpty(value) || !value.equals(nonce);
		if (result) {
			addNonce(nonce);
		}
		return result;
	}

	private static void addNonce(String nonce) {
		redisUtil.set(nonce, nonce, TIMEOUT / 1000);

	}

	public static boolean checkSignature(String params, String signature) {
		if (StringUtils.isEmpty(signature)) {
			return false;
		}
		/**
		 * 客户端 Signature 签名是否对应
		 *
		 * 服务端 SECTRT
		 * 拼接签名 后的加密 是否对应 相等
		 * */
		String serverSignature = MD5Util.encrypt(params, SECTRT);
		log.debug("server-Signature->" + serverSignature);
		return serverSignature.equalsIgnoreCase(signature);
	}

	public static boolean checkTimestamp(String timestamp) {
		if (StringUtils.isEmpty(timestamp) || !Pattern.compile("^[-\\+]?[\\d]*$").matcher(timestamp).matches()) {
			return false;
		}
		long nowTime = System.currentTimeMillis();
		return Math.abs(nowTime - Long.valueOf(timestamp)) < TIMEOUT;
	}

	@Value("${secret:alonginfo}")
	public void setSecret(String secret) {
		SECTRT = secret;
	}

	@Value("${timestamp.timeout:180000}")
	public void setTimeout(long timeout) {
		TIMEOUT = timeout;
	}

	@Autowired
	private void setRedisUtil(RedisUtil redisUtil) {
		CheckRequestUtil.redisUtil = redisUtil;
	}

}
