package com.farmacos.ecommerce.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityCliente extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
				"select email as username, senha as password, 1 as enable from cliente where email=?")
		.authoritiesByUsernameQuery(
				"select email as username, 'cliente' as authority from cliente where email=?")
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.antMatcher("/finalizar/**").authorizeRequests().anyRequest().hasAnyAuthority("cliente").and().csrf()
	.disable().formLogin().loginPage("/cliente/login").permitAll().failureUrl("/cliente/login")
	.loginProcessingUrl("/finalizar/login").defaultSuccessUrl("/finalizar").usernameParameter("username")
	.passwordParameter("password").and().logout()
	.logoutRequestMatcher(new AntPathRequestMatcher("/finalizar/logout")).logoutSuccessUrl("/").permitAll()
	.and().exceptionHandling().accessDeniedPage("/negado");
}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("produtos", registry);
	}

	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();

		if (dirName.startsWith("../")) {
			dirName = dirName.replace("../", "");
		}

		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
	}

}
