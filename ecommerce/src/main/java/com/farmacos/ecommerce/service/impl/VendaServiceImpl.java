package com.farmacos.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Venda;
import com.farmacos.ecommerce.repository.ClienteRepository;
import com.farmacos.ecommerce.repository.VendaRepository;
import com.farmacos.ecommerce.service.ClienteService;
import com.farmacos.ecommerce.service.VendaService;
import org.springframework.data.domain.Sort;


@Service
public class VendaServiceImpl implements VendaService{

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Venda> getAllVendaByClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Venda> findPaginated(int pageNo, int pageSize, String email) {
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("dataCompra").descending());
		
		Cliente cliente = this.clienteRepository.findByEmail(email);
		
		if(cliente != null) {
			return this.vendaRepository.findByCliente(cliente, pageable);
		}else {
			return this.vendaRepository.findAll(pageable);
		}
			
	}
	
}
