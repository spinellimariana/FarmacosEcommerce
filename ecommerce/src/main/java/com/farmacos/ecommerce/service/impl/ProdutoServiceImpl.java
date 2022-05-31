/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.service.impl;
import java.io.*;
import java.nio.file.*;
import com.farmacos.ecommerce.enums.AvaliacaoProduto;
import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.exception.ObjectNotFoundException;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.repository.ProdutoRepository;
import com.farmacos.ecommerce.service.ProdutoService;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
            return this.produtoRepository.findByStatus(StatusUsuario.ATIVO, pageable);
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

            if (prod.getStatus() == StatusUsuario.ATIVO) {
                prod.setStatus(StatusUsuario.INATIVO);
            } else if (prod.getStatus() == StatusUsuario.INATIVO) {
                prod.setStatus(StatusUsuario.ATIVO);
            }
            this.produtoRepository.save(prod);

        } catch (Exception e) {
            new Exception("");
        }

    }

    @Override //SALVAR PRODUTO FUNCIONANDO
    public void saveProduto(Produto produto, MultipartFile foto) throws IOException
    {
        	
        String fileName = StringUtils.cleanPath(foto.getOriginalFilename());
        produto.setFoto(fileName);
        
        Produto prod =  this.produtoRepository.save(produto);

        String uploadDir = "produtos/" + prod.getId();
        
        saveFile(uploadDir, fileName, foto);
    }
    
    public static void saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }   
    }

    @Override //alterar produto. Dá pra usar no verProduto também????
    public Produto getProdutoID(long id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        Produto produto = null;

        if (optional.isPresent()) {
            produto = optional.get();
        } else {
            throw new RuntimeException("Produto não encontrato no ID :: " + id);
        }
        return produto;
    }

}
