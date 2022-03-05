package com.farmacos.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.service.UsuarioService;

@Controller
@RequestMapping(value = "usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping 
	public ResponseEntity<Usuario>salvarUsuario(@RequestBody Usuario usuario) throws Exception{
		Usuario user = this.usuarioService.salvarUsuario(usuario);
		return ResponseEntity.ok().body(user);
	} 

}
