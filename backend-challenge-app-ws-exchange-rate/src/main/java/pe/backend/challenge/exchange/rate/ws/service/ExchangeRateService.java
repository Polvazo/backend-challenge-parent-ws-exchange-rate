package pe.backend.challenge.exchange.rate.ws.service;

import java.util.List;

import pe.backend.challenge.exchange.rate.ws.dto.ExchangeRateDTO;
import rx.Single;

public interface ExchangeRateService {

	Single<ExchangeRateDTO> calculate(ExchangeRateDTO exchangeRateDTO);

	Single<List<ExchangeRateDTO>> getAll();

	Single<ExchangeRateDTO> getForId(Long id);
	
	Single<ExchangeRateDTO> create(ExchangeRateDTO exchangeRateDTO);
	
	Single<ExchangeRateDTO> update(Long id, ExchangeRateDTO update);

}
