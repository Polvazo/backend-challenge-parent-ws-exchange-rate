package pe.backend.challenge.exchange.rate.ws.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ExchangeRateDTO {
	
	private Long id;
	private AmountDTO to;
	private AmountDTO from;
	private String type;
	private BigDecimal buyValue;
	private BigDecimal saleValue;
	private BigDecimal amountExchangeRate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateExchangeRate;

}
