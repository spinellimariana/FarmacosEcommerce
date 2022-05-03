package com.farmacos.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacos.ecommerce.model.ItensVenda;

@Repository

public interface ItensVendaRepository extends JpaRepository<ItensVenda, Long>{

}
