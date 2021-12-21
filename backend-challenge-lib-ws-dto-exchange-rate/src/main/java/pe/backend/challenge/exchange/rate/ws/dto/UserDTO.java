package pe.backend.challenge.exchange.rate.ws.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private String token;
	
}