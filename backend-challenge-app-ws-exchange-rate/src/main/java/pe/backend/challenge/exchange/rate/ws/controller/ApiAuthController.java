package pe.backend.challenge.exchange.rate.ws.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.backend.challenge.exchange.rate.ws.controller.util.ApplicationConstants;
import pe.backend.challenge.exchange.rate.ws.controller.util.ApplicationEndPoints;
import pe.backend.challenge.exchange.rate.ws.dto.UserDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RequestMapping(ApplicationEndPoints.API_AUTH)
@RestController
public class ApiAuthController {
	
	private final static String TOKEN_ID = "tokenJWT";
	private final static String PREFIX = "Bearer ";
	private final static String SECRET = "keyBCP";
	
	@PostMapping(ApplicationEndPoints.RESOURCE_USER)
	public UserDTO login(@RequestBody UserDTO userDTO) {
		
		String token = getJWTToken(userDTO.getUsername());
		userDTO.setToken(token);
		return userDTO;
	}
	
	private String getJWTToken(String username) {
		String secretKey = SECRET;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId(TOKEN_ID).setSubject(username)
			    .claim(ApplicationConstants.PARAM_AUTHORITIES, grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return PREFIX + token;
	}
}
