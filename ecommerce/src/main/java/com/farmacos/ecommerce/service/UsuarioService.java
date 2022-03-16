package com.farmacos.ecommerce.service;

import java.util.List;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.request.UsuarioRequest;
import com.farmacos.ecommerce.model.dto.response.UsuarioResponse;
import com.farmacos.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService{


    //public Usuario salvarUsuario(Usuario usuario); 	
    public UsuarioResponse alterarUsuario(long id, UsuarioRequest usuario);

    public void status(Long id);

    //COM THYMELEAF ABAIXO
    public List<Usuario> getAllUsuarios();

    void saveUsuario(Usuario usuario);

    Usuario getUsuarioID(long id);

    //void ativoInativo(long id);
    Page<Usuario> findPaginated(int pageNo, int pageSize);
    
    public List<Usuario> findUsuario(String keyword);


    }


