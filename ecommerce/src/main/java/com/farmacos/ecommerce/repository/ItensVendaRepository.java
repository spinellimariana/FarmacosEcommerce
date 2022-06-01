package com.farmacos.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmacos.ecommerce.model.ItensVenda;
import com.farmacos.ecommerce.model.Venda;

@Repository

public interface ItensVendaRepository extends JpaRepository<ItensVenda, Long>{

	List<ItensVenda> findByVenda(Venda venda);
	
	
}
