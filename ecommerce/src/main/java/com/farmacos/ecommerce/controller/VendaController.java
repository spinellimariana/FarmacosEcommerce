package com.farmacos.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.model.Venda;
import com.farmacos.ecommerce.model.dto.response.VendaResponse;
import com.farmacos.ecommerce.service.VendaService;

@Controller
@RequestMapping("/pedido")
public class VendaController {
	private Cliente cliente;
	
	@Autowired
	private VendaService vendaService;
	

	
    //listar todos usuários
    @GetMapping()
    public String viewHomePage(Model model, @RequestParam(value="keyword", required = false) String keyword) {
        return findPaginated(1, model);
    }
    
    @GetMapping("/showVerPedido/{id}") //visualizar pagina do produto
    public String showVerProduto(@PathVariable(value = "id") long id, Model model) {
        VendaResponse pedido = vendaService.findVendaByid(id);
        model.addAttribute("pedido", pedido);
        return "verPedido";
    }

    @GetMapping("/page/{pageNo}") //listar todos os usuários com paginação
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;

        Page<Venda> page = vendaService.findPaginated(pageNo, pageSize, buscarUsuarioAutenticado());
        List<Venda> listPedidos = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listaPedidos", listPedidos);

        return "/todosPedidos";

    }
    
	private String buscarUsuarioAutenticado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			return autenticado.getName();
			
		}
		return null;

	}
	
}
