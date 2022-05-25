package com.farmacos.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.farmacos.ecommerce.model.Venda;

public interface VendaService {

    public List<Venda> getAllVendaByClient(); //listar

	public Page<Venda> findPaginated(int pageNo, int pageSize, String email);

}
