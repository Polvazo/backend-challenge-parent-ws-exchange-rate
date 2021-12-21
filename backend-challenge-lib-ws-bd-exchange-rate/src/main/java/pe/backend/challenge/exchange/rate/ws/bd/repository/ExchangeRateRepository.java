package pe.backend.challenge.exchange.rate.ws.bd.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.backend.challenge.exchange.rate.ws.bd.entity.EntityExchangeRate;


@Repository
public interface ExchangeRateRepository extends JpaRepository<EntityExchangeRate, Long> {

}
