package pe.backend.challenge.exchange.rate.ws.dto.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ErrorResponseDetail<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String type;

	private String code;

	private String description;

	private String message;

	private String object;

	private String field;

	private T data;
}
