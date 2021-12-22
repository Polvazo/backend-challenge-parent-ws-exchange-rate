package pe.backend.challenge.exchange.rate.ws.util;

public interface ApplicationConstants {

	String PARAM_AUTHORITIES = "authorities";
	String PARAM_SALE = "COMPRA";
	String PARAM_BUY = "VENTA";
	String HEADER_VALUE_TOKEN = "bcp-token";
	String SYSTEM_ACCOUNT = "ADMIN";
	
	
	//MENSAJES DE LA APLICACIÓN
	String NOT_FOUND_KEY = "";
	String RESPONSE_SUCCESS = "message.response.success";
	String RESPONSE_ERROR = "message.response.error";
	String INVALID_CREDENTIALS = "message.invalid.credentials";
	String NOT_FOUND_RESOURCE = "message.not.found.resource";
	
	//DATOS DE LA APLICACIÓN
	String SPRING_APPLICATION_VERSION = "spring.application.version";
	String SPRING_PROFILE_ACTIVE = "spring.profiles.active";
}
