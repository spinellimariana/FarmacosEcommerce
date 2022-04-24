package com.farmacos.ecommerce.model.dto.response;

import org.modelmapper.ModelMapper;

import com.farmacos.ecommerce.model.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

	private String nome;
	private String cpf;
	private String email;
	private String genero;
	
	public ClienteResponse(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.genero = cliente.getGenero();
		
	
	}
	
	
	public Cliente toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		Cliente entity = modelMapper.map(this, Cliente.class);
		return entity;
	}
}
