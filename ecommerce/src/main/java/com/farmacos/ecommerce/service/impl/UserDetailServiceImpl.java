package com.farmacos.ecommerce.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Role;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.repository.ClienteRepository;
import com.farmacos.ecommerce.repository.UsuarioRepository;


@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Collection<Role> collect = Arrays.asList(new Role("USER"));
		
		Cliente user = clienteRepository.findByEmail(username);
		if (user != null) {
			return new User(user.getEmail(), user.getSenha(), mapRoleAuthorities(collect));
		} else {
			Usuario usu = usuarioRepository.findByEmail(username);
			if(usu != null) {
				return new User(usu.getEmail(), usu.getSenha(), mapRoleAuthorities(usu.getRole()));
			}
		}
		 throw new UsernameNotFoundException("User '" + username + "' not found");	}
	
	private Collection<? extends GrantedAuthority> mapRoleAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
