package com.farmacos.ecommerce.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.repository.ProdutoRepository;
import com.farmacos.ecommerce.service.impl.ProdutoServiceImpl;

@SpringBootTest
public class ProdutoServiceImplTest {
	
	@InjectMocks
	private ProdutoServiceImpl produtoService;
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	List<Produto> produtosList = new ArrayList<>();
	Produto produto1 = new Produto();
	Produto produto2 = new Produto();

	
    @BeforeEach
    void setMockOutput() {
    	produto1.setId(1l);
    	produto1.setStatus(StatusUsuario.ATIVO);
    	produtosList.add(produto1);
    	
    	produto2.setId(1l);
    	produto2.setStatus(StatusUsuario.INATIVO);
    	produtosList.add(produto2);
    	
    	
    }

	@Test
	public void testFindAllProductsTest() {
	
		when(this.produtoRepository.findAll()).thenReturn(produtosList);
		
		assertThat(this.produtoService.getAllProdutos());
		
	}
	
	@Test
	public void testFindProductSearchTest() {
		
		when(this.produtoRepository.search(Mockito.any())).thenReturn(produtosList);
	
		when(this.produtoRepository.findAll()).thenReturn(produtosList);
		
		assertThat(this.produtoService.findProduto(""));
		
	}
	
	@Test
	public void testFindProductWithNullValueTest() {
		
		when(this.produtoRepository.search(Mockito.any())).thenReturn(produtosList);
	
		when(this.produtoRepository.findAll()).thenReturn(produtosList);
		
		assertThat(this.produtoService.findProduto(null));
		
	}
	
	@Test
	public void usuarioAtivoTest() {
		when(this.produtoRepository.findById(Mockito.any())).thenReturn(Optional.of(produto1));
		
		when(this.produtoRepository.save(Mockito.any())).thenReturn(produto1);
		
		this.produtoService.status(Mockito.any());		
	}
	
	@Test
	public void usuarioInativoTest() {
		when(this.produtoRepository.findById(Mockito.any())).thenReturn(Optional.of(produto2));
		
		when(this.produtoRepository.save(Mockito.any())).thenReturn(produto2);
		
		this.produtoService.status(Mockito.any());		
	}
	
	@Test
	public void getProdutoIDTest() {
		
		when(this.produtoRepository.findById(Mockito.any())).thenReturn(Optional.of(produto1));
		
		assertThat(this.produtoService.getProdutoID(1l));
		
	}
	
}
