package com.farmacos.ecommerce.model.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.farmacos.ecommerce.model.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {

	private String nome;
	private String telefone;
	private String email;
	private Date dtNascimento;
	private String cargo;
	private String status;
	private String CPF;
	private String senha;
	
	public UsuarioResponse(Usuario usuario) {
		this.nome = usuario.getNome();
		this.telefone = usuario.getNome();
		this.dtNascimento = usuario.getDtNascimento();
		this.cargo = usuario.getCargo();
		this.status = usuario.getStatus();
	}

	public Usuario toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		Usuario entity = modelMapper.map(this, Usuario.class);
		return entity;
	}
}
