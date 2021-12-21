package pe.backend.challenge.exchange.rate.ws.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ExchangeRateDTO {
	
	private AmountDTO origin;
	private AmountDTO destination;
	private String type;
	private BigDecimal exchangeRate;
	private LocalDate dateExchangeRate;

}
