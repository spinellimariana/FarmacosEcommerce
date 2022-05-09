/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.controller;

import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.service.ClienteService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView cadastrar(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cadastroCliente");
        mv.addObject("cliente", cliente);    	
        return mv;
    }

    @PostMapping("/saveCliente") //salvar usuario no BD
    public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "redirect:/cliente/login";    
    }

    @GetMapping("/conta")
    public String showIndexContaCliente(Model model
    ) {
        return "minhaConta";
    }

    @GetMapping("/atualizar/{id}")
    public ModelAndView showAtualizarClienteForm(@PathVariable(value = "id") Long id, Model model) {
        Cliente cliente = clienteService.findById(id);
        return cadastrar(cliente);
    }

}
