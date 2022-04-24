package com.farmacos.ecommerce.model.dto.response;

import org.modelmapper.ModelMapper;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {

	private String nome;
	private String telefone;
	//private Date dtNascimento;
        private String dtNascimento;
	private String cargo;
	private StatusUsuario status;
	
	public UsuarioResponse(Usuario usuario) {
		this.nome = usuario.getNome();
		this.telefone = usuario.getTelefone();
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
