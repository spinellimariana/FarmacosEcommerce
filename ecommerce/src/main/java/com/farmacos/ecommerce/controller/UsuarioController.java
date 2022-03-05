package com.farmacos.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.UsuarioResponse;
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
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios(){
		return ResponseEntity.ok().body(usuarioService.getAllUsuarios());
	}
	
	@PutMapping
	public ResponseEntity<UsuarioResponse> alterarUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(this.usuarioService.alterarUsuario(usuario));
	}
	
	
}
