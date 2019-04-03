package com.yx.common.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于封装通用返回信息
 */
public class Result extends HashMap<String, Object> {

	public Result() {
		put("code", 200);
		put("msg", "操作成功");
	}

	public static Result ok(String msg) {
		Result result = new Result();
		result.put("msg", msg);
		return result;
	}

	public static Result ok(Map<String, Object> map) {
		Result result = new Result();
		result.putAll(map);
		return result;
	}

	public static Result ok() {
		return new Result();
	}

	public static Result error() {
		return error(500, "操作失败");
	}

	public static Result error(String msg) {
		return error(500, msg);
	}

	public static Result error(int code, String msg) {
		Result result = new Result();
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
