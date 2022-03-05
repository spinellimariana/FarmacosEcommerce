package com.farmacos.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.UsuarioResponse;
import com.farmacos.ecommerce.repository.UsuarioRepository;
import com.farmacos.ecommerce.service.UsuarioService;

import enums.StatusUsuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public UsuarioResponse alterarUsuario(Usuario usuario) {
		UsuarioResponse usuResponse = new UsuarioResponse(usuario);
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));		
		return usuResponse;
	}

	@Override
	public Usuario status(Long id, StatusUsuario status) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Usuario> getAllUsuarios() {

		return usuarioRepository.findAll();
	}

}
