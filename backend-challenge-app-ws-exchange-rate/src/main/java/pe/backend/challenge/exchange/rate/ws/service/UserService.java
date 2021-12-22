package pe.backend.challenge.exchange.rate.ws.service;

import pe.backend.challenge.exchange.rate.ws.dto.UserDTO;
import rx.Single;

public interface UserService {
	
	Single<UserDTO> login(UserDTO userDTO);

}
