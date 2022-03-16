package com.farmacos.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maris
 */
@Controller
public class IndexController {
    
    @GetMapping("/")
    public String showIndex(){
        return "index";
    }
	
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
}
