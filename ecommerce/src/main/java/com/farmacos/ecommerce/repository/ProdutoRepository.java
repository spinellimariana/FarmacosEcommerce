/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.repository;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author maris
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    //listar e pesquisar com paginação
    @Query("SELECT u FROM Produto u WHERE u.nome like %?1%")
    public List<Produto> search(String keyword);

    @Query("SELECT u FROM Produto u WHERE u.nome like %?1%")
    public Page<Produto> search(String keyword, Pageable page);
    
    public Page<Produto> findByStatus(StatusUsuario status, Pageable page);

}
