package com.farmacos.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacos.ecommerce.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{
    
    
}
