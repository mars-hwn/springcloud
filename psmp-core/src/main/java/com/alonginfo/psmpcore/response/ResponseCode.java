package com.alonginfo.psmpcore.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 响应状态码
 *
 * @author rayjp
 * @create 2018-05-28
 **/
public enum ResponseCode {

	/* 成功状态码 */
	SUCCESS(200, null),

	/* token错误：10001-10099*/
	TOKEN_NULL(10001, "没有Token"),

	TOKEN_INVAILD(10002, "Token 已失效"),

	/* 用户错误：20001-29999*/
	USER_NOT_LOGGED_IN(20001, "用户未登录"),

	USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),

	USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),

	USER_NOT_EXIST(20004, "用户不存在"),

	USER_HAS_EXISTED(20005, "用户已存在"),

	/* 业务错误：30001-39999 */
	SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),

	/* 系统错误：40001-49999 */
	SYSTEM_INNER_ERROR(40001, "系统异常，请稍后重试"),

	/* 数据错误：50001-599999 */
	RESULE_DATA_NONE(50001, "数据未找到"),

	DATA_IS_WRONG(50002, "数据有误"),

	DATA_ALREADY_EXISTED(50003, "数据已存在"),

	/* 接口错误：60001-69999 */
	INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),

	INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),

	INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),

	INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),

	INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),

	INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

	/* 权限错误：70001-79999 */
	PERMISSION_NO_ACCESS(70001, "无访问权限"),
	PERMISSION_TIME_OUT(70002,"过期的请求"),
	PERMISSION_NO_NONCE(70003,"重复的请求"),
	PERMISSION_SIGN_ERROR(70004,"签名不正确的请求");

	private Integer code;
	private String message;

	ResponseCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static Integer getCode(String name) {
		for (ResponseCode item : ResponseCode.values()) {
			if (item.name().equals(name)) {
				return item.code;
			}
		}
		return null;
	}

	public static String getMessage(String name) {
		for (ResponseCode item : ResponseCode.values()) {
			if (item.name().equals(name)) {
				return item.message;
			}
		}
		return name;
	}

	//校验重复的code值
	public static void main(String[] args) {
		ResponseCode[] resultCodes = ResponseCode.values();
		List<Integer> codeList = new ArrayList<Integer>();
		for (ResponseCode resultCode : resultCodes) {
			if (codeList.contains(resultCode.code)) {
				System.out.println(resultCode.code);
			} else {
				codeList.add(resultCode.code());
			}
		}
	}

	public Integer code() {
		return this.code;
	}

	public String message() {
		return this.message;
	}

	@Override
	public String toString() {
		return this.name();
	}

}
