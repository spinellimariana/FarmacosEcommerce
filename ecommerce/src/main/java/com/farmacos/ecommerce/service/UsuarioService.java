package com.farmacos.ecommerce.service;

import java.util.List;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.request.UsuarioRequest;
import com.farmacos.ecommerce.model.dto.response.UsuarioResponse;
import org.springframework.data.domain.Page;


public interface UsuarioService {

	//public Usuario salvarUsuario(Usuario usuario); 	
	
	public UsuarioResponse alterarUsuario(long id, UsuarioRequest usuario);
	
	public UsuarioResponse status(Long id, StatusUsuario status);
	
	
        //COM THYMELEAF ABAIXO
        public List<Usuario> getAllUsuarios();
                
        void saveUsuario(Usuario usuario);
        
        Usuario getUsuarioID(long id);
        
        //void ativoInativo(long id);
        
        Page<Usuario> findPaginated(int pageNo, int pageSize);
        

        
        
}
