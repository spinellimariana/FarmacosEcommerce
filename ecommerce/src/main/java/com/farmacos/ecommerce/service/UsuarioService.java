package com.farmacos.ecommerce.service;

import java.util.List;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.request.UsuarioRequest;
import com.farmacos.ecommerce.model.dto.response.UsuarioResponse;


public interface UsuarioService {

	//public Usuario salvarUsuario(Usuario usuario); 	
	
	public UsuarioResponse alterarUsuario(long id, UsuarioRequest usuario);
	
	public UsuarioResponse status(Long id, StatusUsuario status);
	
	public List<Usuario> getAllUsuarios();
        //List<Usuario> getAllUsuarios();
        
        void saveUsuario(Usuario usuario);
        
        
}
