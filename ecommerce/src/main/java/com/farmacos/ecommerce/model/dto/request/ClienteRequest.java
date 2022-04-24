package com.farmacos.ecommerce.model.dto.request;

import com.farmacos.ecommerce.model.Cliente;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteRequest {
	
	private String nome;
	private String genero;
	private String senha;

	
	public ClienteRequest(Cliente cliente) {
		
		this.nome = cliente.getNome();
		this.genero = cliente.getGenero();
		
	}
	
}
