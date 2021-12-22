package pe.backend.challenge.exchange.rate.ws.bd.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import pe.backend.challenge.exchange.rate.ws.util.bd.AppConstants;

@Data
@Entity
@Table(name = AppConstants.ENTITY_TABLE_EXCHANGE_RATE, schema=AppConstants.SCHEMA)
public class EntityExchangeRate extends EntityAudit {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{message.fromCurrency.notempty}")
    @Column(name="CURRENCY_ORIGIN")
	private String fromCurrency;

	@NotBlank(message = "{message.toCurrency.notempty}")
    @Column(name="CURRENCY_DESTINATION")
	private String toCurrency;
	
    @Column(name="DATE_EXCHANGE_RATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;

	@Column(name="SALE_VALUE")
	private BigDecimal saleValue;
	
	@Column(name="BUY_VALUE")
	private BigDecimal buyValue;
	
}
