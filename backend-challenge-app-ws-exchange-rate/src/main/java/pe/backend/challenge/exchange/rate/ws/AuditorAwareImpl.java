package pe.backend.challenge.exchange.rate.ws;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

import pe.backend.challenge.exchange.rate.ws.util.ApplicationConstants;
import pe.backend.challenge.exchange.rate.ws.util.SecurityUtils;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor(){
    	  String userName = SecurityUtils.getCurrentUserLogin();
          return userName != null ? Optional.of(userName) : Optional.of(ApplicationConstants.SYSTEM_ACCOUNT);
    }
    
    /*
 	@Override  
  	public Mono<String> getCurrentAuditor() {  
    	  	return ReactiveSecurityContextHolder.getContext()  
              	  	.map(SecurityContext::getAuthentication)  
              	  	.map(Authentication::getPrincipal)  
              	  	.map(Object::toString);  
  	}*/
    
}