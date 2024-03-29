package com.farmacos.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.farmacos.ecommerce.enums.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VENDA")
@Entity
@Getter
@Setter
public class Venda {

	private static final long serialVersionUID = -3766858690685689916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private Long id;

	@ManyToOne
	private Cliente cliente;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompra;

	private String formaPagamento;
	
	private Double frete = 0.;
	
	private Double valorTotal = 0.;
	
	@Enumerated(value = EnumType.ORDINAL)
	private StatusPedido status;
	
	@ManyToOne
	private Endereco endereco;
	
	
}
