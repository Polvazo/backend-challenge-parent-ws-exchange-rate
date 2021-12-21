package pe.backend.challenge.exchange.rate.ws.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AmountDTO {
	
	private String currency;
	private BigDecimal amount;

}
