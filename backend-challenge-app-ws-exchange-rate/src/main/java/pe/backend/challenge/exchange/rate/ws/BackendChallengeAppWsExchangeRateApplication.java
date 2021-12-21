package pe.backend.challenge.exchange.rate.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.backend.challenge.exchange.rate.ws.controller.util.ApplicationEndPoints;

@SpringBootApplication
public class BackendChallengeAppWsExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendChallengeAppWsExchangeRateApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(WebSecurity web) throws Exception {
			//web.ignoring().antMatchers(ApplicationEndPoints.H2_CONSOLE);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, ApplicationEndPoints.API_AUTH.concat(ApplicationEndPoints.RESOURCE_USER))
					.permitAll()
					.anyRequest().authenticated()
					.and().httpBasic().disable();
		}
	}

}
