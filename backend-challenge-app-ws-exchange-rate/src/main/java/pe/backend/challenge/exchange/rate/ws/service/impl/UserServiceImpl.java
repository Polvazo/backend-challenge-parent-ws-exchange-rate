package pe.backend.challenge.exchange.rate.ws.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.backend.challenge.exchange.rate.ws.bd.entity.EntityUser;
import pe.backend.challenge.exchange.rate.ws.bd.repository.UserRepository;
import pe.backend.challenge.exchange.rate.ws.dto.UserDTO;
import pe.backend.challenge.exchange.rate.ws.exception.InternalException;
import pe.backend.challenge.exchange.rate.ws.service.UserService;
import pe.backend.challenge.exchange.rate.ws.util.ApplicationConstants;
import rx.Single;

import pe.backend.challenge.exchange.rate.ws.util.SecurityUtils;;
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Single<UserDTO> login(UserDTO userDTO) {
		return Single.create(suscriber -> {
			Optional<EntityUser> entityOptional = userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
			if (!entityOptional.isPresent()) 
				suscriber.onError(new InternalException(HttpStatus.UNAUTHORIZED, ApplicationConstants.INVALID_CREDENTIALS));
			else {
				String token = SecurityUtils.getJWTToken(userDTO.getUsername());
				userDTO.setToken(token);
				suscriber.onSuccess(userDTO);
			}
		});
	}

}
