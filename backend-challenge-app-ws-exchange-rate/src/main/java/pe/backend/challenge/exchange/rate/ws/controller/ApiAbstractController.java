package pe.backend.challenge.exchange.rate.ws.controller;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import pe.backend.challenge.exchange.rate.ws.dto.generic.response.GenericResponseDTO;
import pe.backend.challenge.exchange.rate.ws.util.ApplicationConstants;

public abstract class ApiAbstractController {

	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	protected Environment environment;

	protected <T> GenericResponseDTO<T> buildApiResponse(HttpStatus code, String message) {
		GenericResponseDTO<T> apiGenericResponse = new GenericResponseDTO<T>();
		apiGenericResponse.setStatus(code);
		apiGenericResponse.setMessage(getValueMessage(message));
		return apiGenericResponse;
	}
	
	protected <T> GenericResponseDTO<T> buildApiResponse(HttpStatus code, String message, T data) {
		GenericResponseDTO<T> apiGenericResponse = new GenericResponseDTO<T>();
		apiGenericResponse.setStatus(code);
		apiGenericResponse.setMessage(getValueMessage(message));
		apiGenericResponse.setData(data);
		return apiGenericResponse;
	}
	
	protected <T> GenericResponseDTO<T> buildApiResponse(HttpStatus code, String message, Object[] parameters, T data) {
		GenericResponseDTO<T> apiGenericResponse = new GenericResponseDTO<T>();
		apiGenericResponse.setStatus(code);
		apiGenericResponse.setMessage(getValueMessage(message, parameters));
		apiGenericResponse.setData(data);
		return apiGenericResponse;
	}

	protected String getValueMessage(String key) {
		return messageSource.getMessage(key, new Object[] {}, ApplicationConstants.NOT_FOUND_KEY, Locale.getDefault());
	}

	protected String getValueMessage(String key, Object[] parameters) {
		return messageSource.getMessage(key, parameters, ApplicationConstants.NOT_FOUND_KEY, Locale.getDefault());
	}

	protected String getValueConfig(String key) {
		return environment.getProperty(key, ApplicationConstants.NOT_FOUND_KEY);
	}
}