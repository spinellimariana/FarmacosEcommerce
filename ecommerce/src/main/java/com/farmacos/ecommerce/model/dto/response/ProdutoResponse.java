package com.farmacos.ecommerce.model.dto.response;

import java.io.File;

import org.modelmapper.ModelMapper;

import com.farmacos.ecommerce.enums.AvaliacaoProduto;
import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.model.Usuario;

import lombok.Data;

@Data
public class ProdutoResponse {
	
	private String nome;

	private int qtdEstoque = 0;

	private double valorVenda;

	private StatusUsuario status;
	private String descricao;

	private AvaliacaoProduto avaliacao;

	private File foto;
	
	public ProdutoResponse(Produto produto, File image) {
		this.nome = produto.getNome();
		this.qtdEstoque = produto.getQtdEstoque();
		this.valorVenda = produto.getValorVenda();
		this.descricao = produto.getDescricao();
		this.status = produto.getStatus();
		this.avaliacao = produto.getAvaliacao();
		this.foto = image;
	}

	public Usuario toEntity() {
		ModelMapper modelMapper = new ModelMapper();
		Usuario entity = modelMapper.map(this, Usuario.class);
		return entity;
	}

}
