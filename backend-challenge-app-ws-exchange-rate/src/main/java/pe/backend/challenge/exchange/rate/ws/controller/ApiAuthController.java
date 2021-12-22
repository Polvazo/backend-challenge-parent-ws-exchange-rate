package pe.backend.challenge.exchange.rate.ws.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.backend.challenge.exchange.rate.ws.dto.ExchangeRateDTO;
import pe.backend.challenge.exchange.rate.ws.dto.UserDTO;
import pe.backend.challenge.exchange.rate.ws.dto.generic.response.GenericResponseDTO;
import pe.backend.challenge.exchange.rate.ws.service.ExchangeRateService;
import pe.backend.challenge.exchange.rate.ws.service.UserService;
import pe.backend.challenge.exchange.rate.ws.util.ApplicationConstants;
import pe.backend.challenge.exchange.rate.ws.util.ApplicationEndPoints;
import rx.Single;
import rx.schedulers.Schedulers;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RequestMapping(ApplicationEndPoints.API_AUTH)
@RestController
public class ApiAuthController  extends ApiAbstractController {
	
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	@PostMapping(ApplicationEndPoints.RESOURCE_USER_AUTH)
	public Single<GenericResponseDTO<UserDTO>>  login(@RequestBody UserDTO userDTO) {		
		return userService.login(userDTO).subscribeOn(Schedulers.io())
				.map(e -> buildApiResponse(HttpStatus.OK, ApplicationConstants.RESPONSE_SUCCESS, e));
	}
}
