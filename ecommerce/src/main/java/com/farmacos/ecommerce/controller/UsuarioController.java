package com.farmacos.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.repository.UsuarioRepository;
import com.farmacos.ecommerce.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuario") //o que esta no get maping
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //listar todos usuários
    @GetMapping()
    public String viewHomePage(Model model, @RequestParam(value="keyword", required = false) String keyword) {
        return findPaginated(1, model, keyword);
        //chamo o método paginated abaixo e aí ele dá o display de todos os usuários

        /*
        //CÓDIGO ANTIGO SEM PAGINAÇÃO
        model.addAttribute("listaUsuarios", usuarioService.getAllUsuarios());
        return "todosUsuarios";
         */
    }

    @GetMapping("/page/{pageNo}") //listar todos os usuários com paginação
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam(value="keyword", required = false) String keyword) {
        int pageSize = 10;

        Page<Usuario> page = usuarioService.findPaginated(pageNo, pageSize, keyword);
        List<Usuario> listUsuarios = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listaUsuarios", listUsuarios);

        return "todosUsuarios";

    }

    @GetMapping("/showNewUsuarioForm") //cadastrar novo usuario no BD
    public String showNewUsuarioForm(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "novoUsuario"; // mesmo nome do html
    }

    @PostMapping("/saveUsuario") //salvar usuario no BD
    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.saveUsuario(usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/showFormForUptade/{id}") //atualizar usuario no BD
    public String showFormForUptade(@PathVariable(value = "id") long id, Model model) {
        Usuario usuario = usuarioService.getUsuarioID(id);
        model.addAttribute("usuario", usuario);
        return "atualizarUsuario";
    }

//     @GetMapping("/usuario") //pesquisar o usuario
//     public String pesquisaUsuario(Model model, @RequestParam("keyword") String keyword){
//         model.addAttribute("listaUsuarios", usuarioService.findUsuario(keyword));
//         model.addAttribute("keyword", keyword);
//         return "todosUsuarios";
//     }



    @GetMapping("/ativoInativo/{id}") //metodo para ativar/inativar no BD
    public String ativoInativo(@PathVariable(value = "id") long id, Model model) {
        //chama o metodo que tá na service impl
        usuarioService.status(id);
        //como programar ativação inativação?
        return "redirect:/usuario";
    }

    //CÓDIGO DO VINI
    /*@PatchMapping(value = "{id}")
    public ResponseEntity<UsuarioResponse> alterarUsuario(@RequestBody UsuarioRequest usuario, @PathVariable Long id) {
        return ResponseEntity.ok().body(this.usuarioService.alterarUsuario(id, usuario));
    }*/

 /*@PostMapping
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) throws Exception {
		Usuario user = this.usuarioService.salvarUsuario(usuario);
		return ResponseEntity.ok().body(user);
	}*/

 /*@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		return ResponseEntity.ok().body(usuarioService.getAllUsuarios());
	}*/
}
