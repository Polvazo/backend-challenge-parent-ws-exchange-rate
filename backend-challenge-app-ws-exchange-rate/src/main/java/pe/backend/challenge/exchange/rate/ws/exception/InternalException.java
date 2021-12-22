package pe.backend.challenge.exchange.rate.ws.exception;

import org.springframework.http.HttpStatus;

public class InternalException extends Exception {
	private static final long serialVersionUID = 1L;

	private HttpStatus statusCode;
	private String messageKey;
	private Object[] messageParameters;

	public InternalException() {
		super();
	}

	public InternalException(Throwable cause) {
		super(cause);
	}

	public InternalException(Throwable cause, HttpStatus statusCode, String messageKey) {
		this(cause);
		this.statusCode = statusCode;
		this.messageKey = messageKey;
	}

	public InternalException(Throwable cause, HttpStatus statusCode, String messageKey,
			Object[] messageParameters) {
		this(cause, statusCode, messageKey);
		this.messageParameters = messageParameters;
	}

	public InternalException(HttpStatus statusCode, String messageKey) {
		this.statusCode = statusCode;
		this.messageKey = messageKey;
	}

	public InternalException(HttpStatus statusCode, String messageKey,
			Object[] messageParameters) {
		this(statusCode, messageKey);
		this.messageParameters = messageParameters;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public Object[] getMessageParameters() {
		return messageParameters;
	}
}
