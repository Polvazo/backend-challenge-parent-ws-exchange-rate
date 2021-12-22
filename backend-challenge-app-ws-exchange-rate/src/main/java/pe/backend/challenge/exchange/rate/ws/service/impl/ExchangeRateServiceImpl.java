package pe.backend.challenge.exchange.rate.ws.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.backend.challenge.exchange.rate.ws.bd.repository.ExchangeRateRepository;
import pe.backend.challenge.exchange.rate.ws.bd.entity.EntityExchangeRate;
import pe.backend.challenge.exchange.rate.ws.dto.AmountDTO;
import pe.backend.challenge.exchange.rate.ws.dto.ExchangeRateDTO;
import pe.backend.challenge.exchange.rate.ws.exception.InternalException;
import pe.backend.challenge.exchange.rate.ws.service.ExchangeRateService;
import pe.backend.challenge.exchange.rate.ws.util.ApplicationConstants;
import reactor.core.publisher.Mono;
import rx.Single;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	private ExchangeRateRepository exchangeRateRepository;

	@Autowired
	public void setExchangeRateRepository(ExchangeRateRepository exchangeRateRepository) {
		this.exchangeRateRepository = exchangeRateRepository;
	}

	@Override
	public Mono<ExchangeRateDTO> calculate(ExchangeRateDTO exchangeRateDTO) {

		return Mono.create(suscriber -> {
			Optional<EntityExchangeRate> entityOptional = exchangeRateRepository
					.findByFromCurrencyAndToCurrencyAndToDate(exchangeRateDTO.getFrom().getCurrency(),
							exchangeRateDTO.getTo().getCurrency(), exchangeRateDTO.getDateExchangeRate());
			if (!entityOptional.isPresent()) 
				suscriber.error(new InternalException(HttpStatus.NOT_FOUND, ApplicationConstants.NOT_FOUND_RESOURCE));
			else {
				exchangeRateDTO.setBuyValue(entityOptional.get().getBuyValue());
				exchangeRateDTO.setSaleValue(entityOptional.get().getSaleValue());
				if (exchangeRateDTO.getType().equals(ApplicationConstants.PARAM_SALE))
					exchangeRateDTO.getTo().setAmount(
							entityOptional.get().getBuyValue().multiply(exchangeRateDTO.getFrom().getAmount()));
				else if (exchangeRateDTO.getType().equals(ApplicationConstants.PARAM_BUY))
					exchangeRateDTO.getTo().setAmount(
							entityOptional.get().getSaleValue().multiply(exchangeRateDTO.getFrom().getAmount()));
				suscriber.success(exchangeRateDTO);
			}
		});
	}

	@Override
	public Single<List<ExchangeRateDTO>> getAll() {
		return Single.create(suscriber -> {
			List<EntityExchangeRate> listExchangeRate = exchangeRateRepository.findAll();
			if (listExchangeRate.isEmpty()) 
				suscriber.onError(new InternalException(HttpStatus.NOT_FOUND, ApplicationConstants.NOT_FOUND_RESOURCE));
			else {
				List<ExchangeRateDTO> result =listExchangeRate.stream().map( e -> mapperExchangeRateDto(e)).collect(Collectors.toList());
				suscriber.onSuccess(result);
			}
		});
	}

	@Override
	public Single<ExchangeRateDTO> getForId(Long id) {
		return Single.create(suscriber -> {
			Optional<EntityExchangeRate> entityOptional = exchangeRateRepository.findById(id);
			if (!entityOptional.isPresent()) 
				suscriber.onError(new InternalException(HttpStatus.NOT_FOUND, ApplicationConstants.NOT_FOUND_RESOURCE));
			else {
				suscriber.onSuccess(mapperExchangeRateDto(entityOptional.get()));
			}
		});
	}

	@Override
	public Single<ExchangeRateDTO> update(Long id, ExchangeRateDTO update) {
		update.setId(id);
		return Single.create(suscriber -> {
			Optional<EntityExchangeRate> entityOptional = exchangeRateRepository.findById(update.getId());
			if (!entityOptional.isPresent()) 
				suscriber.onError(new InternalException(HttpStatus.NOT_FOUND, ApplicationConstants.NOT_FOUND_RESOURCE));
			else {
				EntityExchangeRate entity = new EntityExchangeRate();
				entityOptional.get().setBuyValue(update.getBuyValue());
				entityOptional.get().setSaleValue(update.getSaleValue());
				entityOptional.get().setFromCurrency(update.getFrom().getCurrency());
				entity = exchangeRateRepository.save(entityOptional.get());
				suscriber.onSuccess(mapperExchangeRateDto(entity));
			}

		});
	}

	@Override
	public Single<ExchangeRateDTO> create(ExchangeRateDTO exchangeRateDTO) {
		return Single.create(suscriber -> {
			if (exchangeRateDTO.getFrom().getCurrency() == null 
					&& exchangeRateDTO.getTo().getCurrency() == null ) 
				suscriber.onError(new InternalException(HttpStatus.NOT_FOUND, ApplicationConstants.RESPONSE_ERROR));
			else {
				EntityExchangeRate entity = new EntityExchangeRate();
				entity.setBuyValue(exchangeRateDTO.getBuyValue());
				entity.setSaleValue(exchangeRateDTO.getSaleValue());
				entity.setFromCurrency(exchangeRateDTO.getFrom().getCurrency());
				entity.setToCurrency(exchangeRateDTO.getTo().getCurrency());
				entity.setToDate(LocalDate.now());
				entity = exchangeRateRepository.save(entity);
				suscriber.onSuccess(mapperExchangeRateDto(entity));
			}
		});
	}
	
	private ExchangeRateDTO mapperExchangeRateDto(EntityExchangeRate entity) {
		ExchangeRateDTO mapper = new ExchangeRateDTO();
		AmountDTO to = new AmountDTO();
		AmountDTO from = new AmountDTO();
		to.setCurrency(entity.getFromCurrency());
		from.setCurrency(entity.getToCurrency());
		mapper.setFrom(to);
		mapper.setTo(from);
		mapper.setBuyValue(entity.getBuyValue());
		mapper.setSaleValue(entity.getSaleValue());
		mapper.setDateExchangeRate(entity.getToDate());
		mapper.setId(entity.getId());
		return mapper;
	}
}
