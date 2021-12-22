package pe.backend.challenge.exchange.rate.ws.bd.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.backend.challenge.exchange.rate.ws.bd.entity.EntityUser;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, Long>{
	
	Optional<EntityUser> findByUsernameAndPassword(String username, String password);

}
