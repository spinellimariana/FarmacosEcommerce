package com.farmacos.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Usuario;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    //essa query está certa?
    @Query("SELECT u FROM Usuario u WHERE u.nome like %?1%")
    public List<Usuario> search(String keyword);
    
    @Query("SELECT u FROM Usuario u WHERE u.nome like %?1%")
    public Page<Cliente> search(String keyword, Pageable page);
    
     Usuario findByEmail(String email);
	
}
