/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.farmacos.ecommerce.enums.StatusPedido;
import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.ItensVenda;
import com.farmacos.ecommerce.model.Produto;
import com.farmacos.ecommerce.model.Venda;
import com.farmacos.ecommerce.repository.ItensVendaRepository;
import com.farmacos.ecommerce.repository.ProdutoRepository;
import com.farmacos.ecommerce.repository.VendaRepository;
import com.farmacos.ecommerce.service.ClienteService;
import com.farmacos.ecommerce.service.ProdutoService;

/**
 *
 * @author 009432631
 */
@Controller
public class CarrinhoController {

	private List<ItensVenda> itensVenda = new ArrayList<ItensVenda>();
	private Venda venda = new Venda();
	private Cliente cliente;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ItensVendaRepository itensVendaRepository;

	private void calcularTotal() {
		venda.setValorTotal(0.);
		for (ItensVenda it : itensVenda) {
			venda.setValorTotal(venda.getValorTotal() + it.getValorTotal());
		}
	}

	@GetMapping("/carrinho")
	public ModelAndView chamarCarrinho() {
		ModelAndView mv = new ModelAndView("carrinho");
		calcularTotal();
		mv.addObject("venda", venda);
		mv.addObject("listaItens", itensVenda);
		return mv;
	}

	@GetMapping("/finalizar")
	public ModelAndView finalizarCompra() {
		buscarUsuarioAutenticado();
		ModelAndView mv = new ModelAndView("finalizar");
		calcularTotal();
		mv.addObject("venda", venda);
		mv.addObject("listaItens", itensVenda);
		mv.addObject("cliente", cliente);
		return mv;
	}

	@PostMapping("/finalizar/confirmar")
	public ModelAndView confirmarCompra(String formaPagamento) {
		ModelAndView mv = new ModelAndView("paginaPrincipal");
		venda.setCliente(cliente);
		venda.setFormaPagamento(formaPagamento);
		venda.setDataCompra(new Date());
		venda.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
		vendaRepository.saveAndFlush(venda);

		for (ItensVenda item : itensVenda) {
			item.setVenda(venda);
			itensVendaRepository.saveAndFlush(item);
		}
		itensVenda = new ArrayList<>();
		venda = new Venda();

		return mv;
	}

	@GetMapping("/alterarQuantidade/{id}/{acao}")
	public String alterarQuantidade(@PathVariable Long id, @PathVariable Long acao) {

		for (ItensVenda it : itensVenda) {
			if (it.getProduto().getId().equals(id)) {
				if (acao == 1) {
					it.setQuantidade(it.getQuantidade() + 1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnidade()));
				} else if (acao == 0) {
					it.setQuantidade(it.getQuantidade() - 1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnidade()));
					if (it.getQuantidade() == 0) {
						itensVenda.remove(it);
					}

				}
				break;
			}
		}

		return "redirect:/carrinho";
	}

	@GetMapping("/removerProduto/{id}")
	public String removerProduto(@PathVariable Long id) {

		for (ItensVenda it : itensVenda) {
			if (it.getProduto().getId().equals(id)) {
				itensVenda.remove(it);
				break;
			}
		}

		return "redirect:/carrinho";
	}

	@GetMapping("/adicionarCarrinho/{id}")
	public String adicionarCarrinho(@PathVariable Long id) {
		System.out.println(id);
		Produto prod = produtoService.getProdutoID(id);
		Produto produto = prod;

		int controle = 0;
		for (ItensVenda it : itensVenda) {
			if (it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade() + 1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnidade()));
				controle = 1;
				break;
			}
		}

		if (controle == 0) {
			ItensVenda item = new ItensVenda();
			item.setProduto(produto);
			item.setValorUnidade(produto.getValorVenda());
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValorTotal(item.getValorTotal() + (item.getQuantidade() * item.getValorUnidade()));
			itensVenda.add(item);
		}
		return "redirect:/carrinho";
	}

	private void buscarUsuarioAutenticado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) {
			String email = autenticado.getName();
			cliente = clienteService.findEmail(email);
		}

	}

}
