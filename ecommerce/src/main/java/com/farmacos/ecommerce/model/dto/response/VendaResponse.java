package com.farmacos.ecommerce.model.dto.response;

import java.util.List;

import com.farmacos.ecommerce.model.ItensVenda;
import com.farmacos.ecommerce.model.Venda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VendaResponse {
	
	private Venda venda;
	
	private List<ItensVenda> itensPedido;
	
	private String frete;

}
