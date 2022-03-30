/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.model;

import com.farmacos.ecommerce.enums.AvaliacaoProduto;
import com.farmacos.ecommerce.enums.StatusUsuario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author maris
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUTO")
@Entity
@Setter
public class Produto {

    private static final long serialVersionUID = -3766858690685689916L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "QTD", nullable = false)
    @Default
    private int qtdEstoque = 0;

    @Column(name = "VALOR", nullable = false)
    private double valorVenda;

    @Column(name = "STS", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private StatusUsuario status;

    @Column(name = "DESCRICAO", length = 2000)
    private String descricao;

    @Column(name = "AVALIACAO")
    @Enumerated(value = EnumType.ORDINAL)
    private AvaliacaoProduto avaliacao;

    //@Lob
    //@Column(name = "FOTO", columnDefinition = "MEDIUMLOB", length = 2000)
    @Column(name = "FOTO", length = 2000)
    private String foto;

    public String getFoto() {
        if (foto == null || id == null) {
            return null;
        }
        return "/produtos/" + id + "/" + foto;
    }

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public StatusUsuario getStatus() {
		return status;
	}

	public String getDescricao() {
		return descricao;
	}

	public AvaliacaoProduto getAvaliacao() {
		return avaliacao;
	}


    
    
    
    
    
    
}


