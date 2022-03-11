package com.farmacos.ecommerce.model.dto.request;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioRequest {

	private String nome;
	private String telefone;
	//private Date dtNascimento;
        private String dtNascimento;
	private String cargo;
	private StatusUsuario status;
	private String senha;
	
	
	
	public UsuarioRequest(Usuario usuario) {
		this.nome = usuario.getNome();
		this.telefone = usuario.getTelefone();
		this.dtNascimento = usuario.getDtNascimento();
		this.cargo = usuario.getCargo();
		this.status = usuario.getStatus();
	}

}
