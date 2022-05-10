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
@Order(2)
public class SecurityAdm extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC_ENDPOINT = { "/js/**", "/css/**", "/img/**", "/produtos/**", "/banner.jpg",
			"/bannerMobile.jpg", "/slide01.jpg", "/slide01small.jpg"

	};

	private static final String[] PUBLIC_ENDPOINT_POST = { "/autenticacao", "/usuarios", "/produto/**", "/index", "/",
			"/page/**", "/cliente/cadastro", "/cliente/login", "/cliente/saveCliente" };

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
				"select email as username, senha as password, 1 as enable from usuario where email=?")
		.authoritiesByUsernameQuery(
				"select email as username, 'adm' as authority from usuario where email=?")
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC_ENDPOINT).permitAll().antMatchers("/login*").permitAll()
				.antMatchers(PUBLIC_ENDPOINT_POST).permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/index", true).permitAll().and().logout()
				.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

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