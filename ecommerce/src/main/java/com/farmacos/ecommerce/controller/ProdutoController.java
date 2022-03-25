/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.controller;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.enums.AvaliacaoProduto;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.repository.ProdutoRepository;
import com.farmacos.ecommerce.service.ProdutoService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author maris
 */
@Controller
@RequestMapping(value = "/produto") //o que esta no get maping
public class ProdutoController {

    
    @Autowired
    private ProdutoService produtoService;
   

    //listar todos usuários
    @GetMapping()
    public String viewHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        return findPaginated(1, model, keyword);
        //chamo o método paginated abaixo e aí ele dá o display de todos os produtos

    }

    @GetMapping("/page/{pageNo}") //listar todos os usuários com paginação
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        int pageSize = 10;

        Page<Produto> page = produtoService.findPaginated(pageNo, pageSize, keyword);
        List<Produto> listProdutos = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listaProdutos", listProdutos);

        return "todosProdutos";

    }

    @GetMapping("/ativoInativo/{id}") //metodo para ativar/inativar no BD
    public String ativoInativo(@PathVariable(value = "id") long id, Model model) {
        //chama o metodo que tá na service impl
        produtoService.status(id);
        return "redirect:/produto";
    }

    @GetMapping("/showNewProdutoForm") //SALVANDO FUNCIONANDO SEM IMAGEM
    public String showNewProdutoForm(Model model) {
        Produto produto = new Produto();
        model.addAttribute("produto", produto);
        return "novoProduto";
    }

    @PostMapping("/saveProduto") //salvar produto funcionando
    public String saveProduto(@ModelAttribute("produto") Produto produto, @RequestParam("file") MultipartFile foto) throws IOException{
        produtoService.saveProduto(produto, foto);
        return "redirect:/produto";
    }
    
    @GetMapping("/showVerProduto/{id}") //visualizar pagina do produto
    public String showVerProduto(@PathVariable(value = "id") long id, Model model) {
        //programar produto
        Produto produto = produtoService.getProdutoID(id);
        model.addAttribute("produto", produto);
        return "verProduto";
    }

}
