package pe.backend.challenge.exchange.rate.ws.dto;

import java.math.BigDecimal;



import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AmountDTO {
	
	@NotBlank(message = "{message.toCurrency.notempty}")
	private String currency;
	private BigDecimal amount;

}
