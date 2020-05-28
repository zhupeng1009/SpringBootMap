package com.example.demo.common.domin;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果集
 * 
 */
public class ReturnResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public ReturnResult() {
		put("code", 0);
		put("msg", "success");
		put("data", "");
	}
	
	public static ReturnResult error() {
		return error(1, "fail");
	}

	public static ReturnResult error(int code, String msg) {
		ReturnResult r = new ReturnResult();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ReturnResult error(String msg) {
		return error(500, msg);
	}

	public static ReturnResult ok(Object object) {
		ReturnResult r = new ReturnResult();
		r.put("data", object);
		return r;
	}


	public static ReturnResult ok(Map<String, Object> map) {
		ReturnResult r = new ReturnResult();
		r.putAll(map);
		return r;
	}

	public static ReturnResult ok() {
		return new ReturnResult();
	}

	public ReturnResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
