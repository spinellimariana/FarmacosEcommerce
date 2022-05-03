package com.farmacos.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ITENS_COMPRA")
@Entity
@Getter
@Setter
public class ItensVenda {

	private static final long serialVersionUID = -3766858690685689916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private Long id;

	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Cliente cliente;
	
	private int quantidade = 0;
	
	private Double valorUnidade = 0.;
	
	private Double valorTotal = 0.;
	
}
