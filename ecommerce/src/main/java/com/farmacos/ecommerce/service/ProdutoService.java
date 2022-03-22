/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.service;

import com.farmacos.ecommerce.enums.AvaliacaoProduto;
import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author maris
 */
public interface ProdutoService {

    public List<Produto> getAllProdutos(); //listar

    Page<Produto> findPaginated(int pageNo, int pageSize, String keyword); //listar com paginaçao

    public List<Produto> findProduto(String keyword); //pesquisar com paginação
    
    public void status(Long id); //status ativo inativo
    
    void saveProduto(Produto produto); //salvar FUNCIONANDO SEM IMAGEM
        
    /*//TENTATIVA DE SALVAR COM IMAGEM
    void saveProduto(String name, int qtd, double preco, StatusUsuario status, String descricao, AvaliacaoProduto avaliacao, MultipartFile foto);
    */
    Produto getProdutoID(long id); //pegar o produto por id para alterar, mostrar etc.

   
    
    
   }
