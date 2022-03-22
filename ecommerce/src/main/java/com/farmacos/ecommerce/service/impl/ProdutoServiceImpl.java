/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.service.impl;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.exception.ObjectNotFoundException;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.repository.ProdutoRepository;
import com.farmacos.ecommerce.service.ProdutoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author maris
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override //listar todos os usuários
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    @Override //paginação
    public Page<Produto> findPaginated(int pageNo, int pageSize, String keyword) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        if (keyword == null) {
            return this.produtoRepository.findAll(pageable);
        }
        return this.produtoRepository.search(keyword, pageable);
    }

    @Override
    public List<Produto> findProduto(String keyword) {

        if (keyword != null) {
            return produtoRepository.search(keyword);
        }
        return produtoRepository.findAll();

    }

    @Override
    public void status(Long id) {
        Produto prod = this.produtoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto não existente"));
        try {

            if (prod.getStatus() == StatusUsuario.ativo) {
                prod.setStatus(StatusUsuario.inativo);
            } else if (prod.getStatus() == StatusUsuario.inativo) {
                prod.setStatus(StatusUsuario.ativo);
            }
            this.produtoRepository.save(prod);

        } catch (Exception e) {
            new Exception("");
        }

    }

    @Override
    public void saveProduto(Produto produto) {
        this.produtoRepository.save(produto);
    }

    @Override //alterar produto. Dá pra usar no verProduto também????
    public Produto getProdutoID(long id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        Produto produto = null;
        
        if(optional.isPresent()){
            produto = optional.get();
        } else {
            throw new RuntimeException("Produto não encontrato no ID :: " + id);
        }
        return produto;
    }
    
    

}
