/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author 009432631
 */
@Controller
@RequestMapping(value = "/carrinho")
public class CarrinhoController {

    @GetMapping()
    public String showCarrinho(Model model) {
        return "carrinho";
    }

}
