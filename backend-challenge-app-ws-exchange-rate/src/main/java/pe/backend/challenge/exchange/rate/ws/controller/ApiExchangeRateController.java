package pe.backend.challenge.exchange.rate.ws.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.backend.challenge.exchange.rate.ws.dto.ExchangeRateDTO;
import pe.backend.challenge.exchange.rate.ws.dto.generic.response.GenericResponseDTO;
import pe.backend.challenge.exchange.rate.ws.service.ExchangeRateService;
import pe.backend.challenge.exchange.rate.ws.util.ApplicationConstants;
import pe.backend.challenge.exchange.rate.ws.util.ApplicationEndPoints;
import rx.Single;
import rx.schedulers.Schedulers;

@RequestMapping(ApplicationEndPoints.API_EXCHANGE_RATE)
@RestController
public class ApiExchangeRateController extends ApiAbstractController {

	private ExchangeRateService exchangeRateService;

	@Autowired
	public void setExchangeRateService(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}
	
	@PostMapping(ApplicationEndPoints.RESOURCE_EXCHANGE_RATE_SERVICE)
	public Single<GenericResponseDTO<ExchangeRateDTO>> calculate(@Valid @RequestBody ExchangeRateDTO exchangeRateDTO) {
		return exchangeRateService.calculate(exchangeRateDTO).subscribeOn(Schedulers.io())
				.map(e -> buildApiResponse(HttpStatus.OK, ApplicationConstants.RESPONSE_SUCCESS, e));
		
	}
	
	@GetMapping(ApplicationEndPoints.RESOURCE_EXCHANGE_RATE_GET_ID)
	public Single<GenericResponseDTO<ExchangeRateDTO>> getForId(@PathVariable("id") Long id){
		return exchangeRateService.getForId(id).subscribeOn(Schedulers.io())
				.map(e -> buildApiResponse(HttpStatus.OK, ApplicationConstants.RESPONSE_SUCCESS, e));
		
	}
	
	
	@GetMapping(ApplicationEndPoints.RESOURCE_EXCHANGE_RATE)
	public Single<GenericResponseDTO<List<ExchangeRateDTO>>> getAll(){
		return exchangeRateService.getAll().subscribeOn(Schedulers.io())
				.map(e -> buildApiResponse(HttpStatus.OK, ApplicationConstants.RESPONSE_SUCCESS, e));
		
	}
	
	@PostMapping(ApplicationEndPoints.RESOURCE_EXCHANGE_RATE)
	public Single<GenericResponseDTO<ExchangeRateDTO>> create(@Valid @RequestBody ExchangeRateDTO exchangeRateDTO){
		return exchangeRateService.create(exchangeRateDTO).subscribeOn(Schedulers.io())
				.map(e -> buildApiResponse(HttpStatus.CREATED, ApplicationConstants.RESPONSE_SUCCESS, e));
		
	}
	
	@PatchMapping(ApplicationEndPoints.RESOURCE_EXCHANGE_RATE_GET_ID)
	public Single<GenericResponseDTO<ExchangeRateDTO>> update(@PathVariable("id") Long id, @RequestBody ExchangeRateDTO exchangeRateDTO){
		return exchangeRateService.update(id, exchangeRateDTO).subscribeOn(Schedulers.io())
				.map(e -> buildApiResponse(HttpStatus.OK, ApplicationConstants.RESPONSE_SUCCESS, e));
		
	}
	

}
