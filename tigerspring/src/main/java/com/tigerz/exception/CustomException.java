package com.tigerz.exception;

/**
 * 
 * CustomException
 * 
 * @Desc: 自定义异常
 * @Company: TigerZ
 * @author Wang Jingci
 * @date 2016年11月24日 上午11:37:38
 */
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	public String message;

	public CustomException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
