package com.farmacos.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO")
@Entity
public class Usuario {

	private static final long serialVersionUID = -3766858690685689916L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "TELEFONE")
	private String telefone;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "DATA_ANIVERSARIO")
	private Date dtNascimento;

	@Column(name = "CARGO", nullable = false)
	private String cargo;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	@Column(name = "STATUS", nullable = false)
	private String status;

	@Column(name = "CPF", nullable = false, unique = true)
	private String CPF;

}
