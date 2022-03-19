/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.service;

import com.farmacos.ecommerce.model.Produto;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author maris
 */
public interface ProdutoService {

    public List<Produto> getAllProdutos();

    Page<Produto> findPaginated(int pageNo, int pageSize);

    public List<Produto> findProduto(String keyword);

}
