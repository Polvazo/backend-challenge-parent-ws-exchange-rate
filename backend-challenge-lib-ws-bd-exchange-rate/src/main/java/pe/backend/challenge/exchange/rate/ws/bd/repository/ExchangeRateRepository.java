package pe.backend.challenge.exchange.rate.ws.bd.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.backend.challenge.exchange.rate.ws.bd.entity.EntityExchangeRate;


@Repository
public interface ExchangeRateRepository extends JpaRepository<EntityExchangeRate, Long> {
	
	Optional<EntityExchangeRate> findByFromCurrencyAndToCurrencyAndToDate(String fromCurrency, String toCurrency, LocalDate date);

}
