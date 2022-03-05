package com.farmacos.ecommerce.service;

import java.util.List;

import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.UsuarioResponse;

import enums.StatusUsuario;


public interface UsuarioService {

	public Usuario salvarUsuario(Usuario usuario); 	
	
	public UsuarioResponse alterarUsuario(Usuario usuario);
	
	public Usuario status(Long id, StatusUsuario status);
	
	public List<Usuario> getAllUsuarios();
}
