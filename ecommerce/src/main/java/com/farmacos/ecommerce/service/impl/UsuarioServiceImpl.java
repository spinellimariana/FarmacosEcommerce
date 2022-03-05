package com.farmacos.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.repository.UsuarioRepository;
import com.farmacos.ecommerce.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		return this.usuarioRepository.save(usuario);
	}
	
}
