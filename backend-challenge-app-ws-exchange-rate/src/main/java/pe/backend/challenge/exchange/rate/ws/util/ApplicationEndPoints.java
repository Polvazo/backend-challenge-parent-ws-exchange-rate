package pe.backend.challenge.exchange.rate.ws.util;

public interface ApplicationEndPoints {
	
	String API_VERSION_AUTH = "/v1";
	String API_AUTH = "/user" + API_VERSION_AUTH;
	String RESOURCE_USER = "/user";
	String RESOURCE_USER_AUTH = RESOURCE_USER + "/login";

	String API_VERSION_EXCHANGE_RATE = "/v1";
	String API_EXCHANGE_RATE = "/exchange-rate" + API_VERSION_EXCHANGE_RATE;
	String RESOURCE_EXCHANGE_RATE = "/exchange-rate";
	String RESOURCE_EXCHANGE_RATE_SERVICE= RESOURCE_EXCHANGE_RATE + "/calculate";
	String RESOURCE_EXCHANGE_RATE_GET_ID= RESOURCE_EXCHANGE_RATE + "/{id}";
	
	
	String API_ACTUATOR = "/actuator/**";

	
}
