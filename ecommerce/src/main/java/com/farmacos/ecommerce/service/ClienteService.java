package com.farmacos.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.request.ClienteRequest;
import com.farmacos.ecommerce.model.dto.response.ClienteResponse;

public interface ClienteService extends UserDetailsService{

    //public Usuario salvarUsuario(Usuario usuario); 	
    public ClienteResponse alterarUsuario(long id, ClienteRequest usuario);

    public void status(Long id);

    //COM THYMELEAF ABAIXO
    public List<Cliente> getAllClientes();

    void saveCliente(Cliente cliente);

    Cliente findEmail(String email);

    //void ativoInativo(long id);
    Page<Cliente> findPaginated(int pageNo, int pageSize, String keyword);
    
    public List<Cliente> findCliente(String keyword);

	public Cliente findById(Long id);

	
}
