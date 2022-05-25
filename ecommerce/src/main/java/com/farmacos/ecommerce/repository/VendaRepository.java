package com.farmacos.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{
    
	Page<Venda> findByCliente(Cliente cliente, Pageable pageable);
}
