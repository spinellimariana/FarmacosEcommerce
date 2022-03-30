/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.farmacos.ecommerce.model.Produto;

/**
 *
 * @author maris
 */
public interface ProdutoService {

    public List<Produto> getAllProdutos(); //listar

    Page<Produto> findPaginated(int pageNo, int pageSize, String keyword); //listar com paginaçao

    public List<Produto> findProduto(String keyword); //pesquisar com paginação
    
    public void status(Long id); //status ativo inativo
    
    void saveProduto(Produto produto, MultipartFile foto) throws IOException; //salvar funcionando com imagem
            
    Produto getProdutoID(long id); //pegar o produto por id para alterar, mostrar etc.

   
    
    
   }
