/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farmacos.ecommerce.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ItensVendaRepository itensVendaRepository;

	private void calcularTotal() {
		venda.setValorTotal(venda.getFrete());
		for (ItensVenda it : itensVenda) {
			venda.setValorTotal(venda.getValorTotal() + it.getValorTotal());
		}
	}

	@GetMapping()
	public ModelAndView chamarCarrinho() {
		ModelAndView mv = new ModelAndView("carrinho");
		populateCarrinho();
		calcularTotal();
		mv.addObject("venda", venda);
		mv.addObject("listaItens", itensVenda);
		return mv;
	}

	private void populateCarrinho() {
		if(itensVenda.isEmpty()) {
			adicionarCarrinho(20l);
			adicionarCarrinho(21l);
			adicionarCarrinho(21l);
			adicionarCarrinho(21l);
			adicionarCarrinho(22l);
			adicionarCarrinho(22l);
			adicionarCarrinho(14l);
			adicionarCarrinho(14l);
			adicionarCarrinho(14l);
			adicionarCarrinho(14l);
			adicionarCarrinho(18l);
			adicionarCarrinho(18l);
			adicionarCarrinho(18l);
			adicionarCarrinho(18l);
			adicionarCarrinho(18l);
		}
	}

	@GetMapping("/finalizar")
	public ModelAndView finalizarCompra() {
		buscarUsuarioAutenticado();
		ModelAndView mv = new ModelAndView("finalizar");
		calcularTotal();
		venda.setEndereco(cliente.getEndereco().get(0));
		mv.addObject("venda", venda);
		mv.addObject("listaItens", itensVenda);
		mv.addObject("cliente", cliente);
		return mv;
	}

	@PostMapping("/finalizar/frete")
	public String acrescentarFrete(Double frete) {

		venda.setFrete(frete);
		buscarUsuarioAutenticado();
		calcularTotal();

		return "redirect:/finalizar";
	}

	@PostMapping("/finalizar/confirmar")
	public String confirmarCompra(String formaPagamento) throws ParseException {
		venda.setCliente(cliente);
		venda.setFormaPagamento(formaPagamento);
		venda.setDataCompra(getDate());
		venda.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

		vendaRepository.saveAndFlush(venda);

		for (ItensVenda item : itensVenda) {
			item.setVenda(venda);
			itensVendaRepository.saveAndFlush(item);
		}
		Long idVenda = venda.getId();
		itensVenda = new ArrayList<>();
		venda = new Venda();

		return "redirect:/pedido/showVerPedido/" + idVenda;
	}

	private Date getDate() throws ParseException {

		String date = format.format(new Date());

		return format.parse(date);
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

		return "redirect:/";
	}

	@GetMapping("/removerProduto/{id}")
	public String removerProduto(@PathVariable Long id) {

		for (ItensVenda it : itensVenda) {
			if (it.getProduto().getId().equals(id)) {
				itensVenda.remove(it);
				break;
			}
		}

		return "redirect:/";
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
		return "redirect:/";
	}

	private void buscarUsuarioAutenticado() {

			cliente = clienteService.findEmail("vini@vini.com");
		
	}

}
