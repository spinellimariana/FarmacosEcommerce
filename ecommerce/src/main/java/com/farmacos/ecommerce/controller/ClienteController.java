/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.controller;

import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author 009432631
 */
@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/login")
    public String showloginClienteForm(Model model) {
        return "loginCliente";
    }

    @GetMapping("/cadastro")
    public String showCadastroClienteForm(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "cadastroCliente";
    }

    @PostMapping("/saveCliente") //salvar usuario no BD
    public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "cadastroEndereco";
         
    }

    @GetMapping("/conta")
    public String showIndexContaCliente(Model model
    ) {
        return "minhaConta";
    }

    @GetMapping("/atualizar")
    public String showAtualizarClienteForm(Model model
    ) {
        return "atualizarCliente";
    }

}
