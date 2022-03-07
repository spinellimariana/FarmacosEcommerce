package com.farmacos.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.exception.ObjectNotFoundException;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.request.UsuarioRequest;
import com.farmacos.ecommerce.model.dto.response.UsuarioResponse;
import com.farmacos.ecommerce.repository.UsuarioRepository;
import com.farmacos.ecommerce.service.UsuarioService;

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
	public UsuarioResponse alterarUsuario(long id,UsuarioRequest usuario) {
		
		Usuario usu = this.usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuario não existente"));
		
		usu.setNome(usuario.getNome());
		usu.setTelefone(usuario.getTelefone());
		usu.setDtNascimento(usuario.getDtNascimento());
		usu.setCargo(usuario.getCargo());
		usu.setStatus(usuario.getStatus());
		usu.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		
		usuarioRepository.save(usu);
		
		UsuarioResponse usuarioResponse = new UsuarioResponse(usu);
		
		return usuarioResponse;
	}

	@Override
	public UsuarioResponse status(Long id, StatusUsuario status) {
		Usuario usu = this.usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuario não existente"));
		try {
			usu.setStatus(status);
			this.usuarioRepository.save(usu);
			

		} catch (Exception e) {
			new Exception("");
		}
		UsuarioResponse usuResponse = new UsuarioResponse(usu);
		return usuResponse;
	}

	@Override
	public List<Usuario> getAllUsuarios() {

		return usuarioRepository.findAll();
	}

}
