package pe.backend.challenge.exchange.rate.ws.exception;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pe.backend.challenge.exchange.rate.ws.dto.error.ErrorResponse;
import pe.backend.challenge.exchange.rate.ws.util.ApplicationConstants;
@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected Environment environment;

	@ExceptionHandler(InternalException.class)
	public ResponseEntity<Object> handleEntityNotFound(InternalException ex, WebRequest request) {

		ErrorResponse response = buildApiErrorResponse(ex.getStatusCode(), ex.getMessageKey());
		response.setDetail(ex.getLocalizedMessage());
		response.setPath(request.getDescription(true));
		return buildResponseEntity(response);
	}

	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorResponse response = buildApiErrorResponse(HttpStatus.BAD_REQUEST, ApplicationConstants.RESPONSE_ERROR);
		response.setDetail(ex.getLocalizedMessage());
		response.setPath(request.getDescription(true));
		return handleExceptionInternal(ex, response, headers, response.getStatus(), request);
	}

	protected ErrorResponse buildApiErrorResponse(HttpStatus code, String message) {
		ErrorResponse response = new ErrorResponse();
		response.setVersion(getValueConfig(ApplicationConstants.SPRING_APPLICATION_VERSION));
		response.setProfile(getValueConfig(ApplicationConstants.SPRING_PROFILE_ACTIVE));
		response.setStatus(code);
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(getValueMessage(message));
		return response;
	}

	protected ResponseEntity<Object> buildResponseEntity(ErrorResponse apiErrorResponse) {
		return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
	}

	protected String getValueMessage(String key) {
		return messageSource.getMessage(key, new Object[] {}, ApplicationConstants.NOT_FOUND_KEY, Locale.getDefault());
	}

	protected String getValueConfig(String key) {
		return environment.getProperty(key, ApplicationConstants.NOT_FOUND_KEY);
	}
}
