package pe.backend.challenge.exchange.rate.ws.exception;

public class ExternalException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private String messageKey;
	private Object[] messageParameters;

	public ExternalException() {
		super();
	}

	public ExternalException(Throwable cause) {
		super(cause);
	}

	public ExternalException(Throwable cause, String messageKey) {
		super(cause);
		this.messageKey = messageKey;
	}

	public ExternalException(Throwable cause, String messageKey, Object[] messageParameters) {
		this(cause, messageKey);
		this.messageParameters = messageParameters;
	}
	
	public ExternalException(String messageKey) {
		this.messageKey = messageKey;
	}

	public ExternalException(String messageKey, Object[] messageParameters) {
		this(messageKey);
		this.messageParameters = messageParameters;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public Object[] getMessageParameters() {
		return messageParameters;
	}

}
