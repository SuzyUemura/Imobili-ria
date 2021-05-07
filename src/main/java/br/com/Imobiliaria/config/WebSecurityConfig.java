package br.com.Imobiliaria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/categoria").hasAnyRole("ADMIN")
				.antMatchers("/catgeoria/{id}").hasAnyRole("ADMIN")
				.antMatchers("/categoria/").hasAnyRole("ADMIN")
				.antMatchers("/negocio").hasAnyRole("ADMIN")
				.antMatchers("/negocio/{id}").hasAnyRole("ADMIN")
				.antMatchers("/negocio/").hasAnyRole("ADMIN")
				.antMatchers("/quarto").hasAnyRole("ADMIN")
				.antMatchers("/quarto/{id}").hasAnyRole("ADMIN")
				.antMatchers("/quarto/").hasAnyRole("ADMIN")
				.antMatchers("/estado").hasAnyRole("ADMIN")
				.antMatchers("/estado/").hasAnyRole("ADMIN")
				.antMatchers("/municipio").hasAnyRole("ADMIN")
				.antMatchers("/municipio/{id}").hasAnyRole("ADMIN")
				.antMatchers("/municioio/").hasAnyRole("ADMIN")
				.antMatchers("/bairro").hasAnyRole("ADMIN")
				.antMatchers("/bairro/{id}").hasAnyRole("ADMIN")
				.antMatchers("/bairro/").hasAnyRole("ADMIN")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
			.defaultSuccessUrl("/home")
			.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/login")
			.permitAll()
			.and()
			.csrf()
			.disable();
			
	}
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder builder) throws Exception {
		builder
			.inMemoryAuthentication()
			.withUser("admin").password("{noop}gft").roles("ADMIN")
			.and()
			.withUser("client").password("{noop}123").roles("USER");
	}
}
