package com.crif.ticketbooking.exception;

import org.springframework.http.HttpStatus;

public class TicketException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus httpStatus;

	public TicketException(String message, Exception e, HttpStatus httpStatus) {
		super(e);
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public TicketException(String message, Throwable e, HttpStatus httpStatus) {
		super(e);
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
