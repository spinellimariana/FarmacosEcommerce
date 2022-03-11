package com.farmacos.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.request.UsuarioRequest;
import com.farmacos.ecommerce.model.dto.response.UsuarioResponse;
import com.farmacos.ecommerce.service.UsuarioService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping(value = "/usuario") //o que esta no get maping
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
        
        //listar todos usuários
        @GetMapping()
        public String viewHomePage(Model model){
            model.addAttribute("listaUsuarios", usuarioService.getAllUsuarios());
            return "todosUsuarios";
        }
        
        @GetMapping("/showNewUsuarioForm")
        public String showNewUsuarioForm(Model model){
            Usuario usuario = new Usuario();
            model.addAttribute("usuario", usuario);
            return "novoUsuario"; // mesmo nome do html
        }
        
        @PostMapping("/saveUsuario") //salvar usuario no BD
        public String saveUsuario(@ModelAttribute("usuario") Usuario usuario){
            usuarioService.saveUsuario(usuario);
            return "redirect:/usuario";
        }

	/*@PostMapping
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) throws Exception {
		Usuario user = this.usuarioService.salvarUsuario(usuario);
		return ResponseEntity.ok().body(user);
	}*/

	/*@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		return ResponseEntity.ok().body(usuarioService.getAllUsuarios());
	}*/

	@PatchMapping(value = "{id}")
	public ResponseEntity<UsuarioResponse> alterarUsuario(@RequestBody UsuarioRequest usuario, @PathVariable Long id) {
		return ResponseEntity.ok().body(this.usuarioService.alterarUsuario(id,usuario));
	}

}
