package com.kcpit.exceptionAdvice;

import lombok.Data;

@Data
public class Message {
	private String errorMessage;
	private Integer errorCode;

	public Message(String errorMessage, Integer errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

}
