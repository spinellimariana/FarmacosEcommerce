/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.controller;

import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 009432631
 */
@Controller
@RequestMapping(value = "/") //o que esta no get maping
public class LojaController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public String viewHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        return findPaginated(1, model, keyword);
        //chamo o método paginated abaixo e aí ele dá o display de todos os produtos
    }

    @GetMapping("/page/{pageNo}") //listar todos os produtos com paginação
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        int pageSize = 12;

        Page<Produto> page = produtoService.findPaginated(pageNo, pageSize, keyword);
        List<Produto> listProdutos = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listaProdutos", listProdutos);

        return "paginaPrincipal";

    }

}
