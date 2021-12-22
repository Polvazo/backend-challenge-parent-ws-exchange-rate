package pe.backend.challenge.exchange.rate.ws;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import pe.backend.challenge.exchange.rate.ws.util.ApplicationConstants;
import pe.backend.challenge.exchange.rate.ws.util.SecurityUtils;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor(){
    	  String userName = SecurityUtils.getCurrentUserLogin();
          return userName != null ? Optional.of(userName) : Optional.of(ApplicationConstants.SYSTEM_ACCOUNT);
    }
}