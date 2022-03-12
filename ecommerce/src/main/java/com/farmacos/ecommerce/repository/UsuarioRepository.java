package com.farmacos.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacos.ecommerce.model.Usuario;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;

@Repository
//@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    //essa query está certa?
    @Query("select u from Usuario u where u.nome like %?1%")
    public List<Usuario> search(String keyword);
    
}
