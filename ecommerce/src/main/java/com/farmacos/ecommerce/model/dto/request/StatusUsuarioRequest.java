package com.farmacos.ecommerce.model.dto.request;

import com.farmacos.ecommerce.enums.StatusUsuario;

import lombok.Data;

@Data
public class StatusUsuarioRequest {
	
	private StatusUsuario status;

}
