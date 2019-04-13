package com.teste.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.dao.VendaRepository;
import com.teste.enums.EnumTipoMovimento;
import com.teste.model.Estoque;
import com.teste.model.Produto;
import com.teste.model.Venda;
import com.teste.model.VendaItem;
import com.teste.utils.Utils;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private EstoqueService estoqueService;

	public List<Venda> listarTodos() {
		final Iterable<Venda> vendas = vendaRepository.findAll();
		List<Venda> vendasList = new ArrayList<>();
		if (vendas != null) {
			for (Venda venda : vendas) {
				vendasList.add(venda);
			}
			return vendasList;
		}
		return null;
	}

	public Venda salvar(final Venda venda) throws Exception {
        this.atualizarEstoqueProduto(venda, EnumTipoMovimento.SAIDA);
		
        return vendaRepository.save(venda);
	}

	public void remover(final Venda venda) throws Exception {
		this.atualizarEstoqueProduto(venda, EnumTipoMovimento.ENTRADA);
		
		vendaRepository.delete(venda);
	}

	public Venda getVendaPorProduto(final Produto produto) {
		final List<Venda> vendas = this.listarTodos();
		if (!Utils.empty(vendas)) {
			for (Venda venda : vendas) {
				final List<VendaItem> itens = venda.getItens();
				for (VendaItem item : itens) {
					if (item.getProduto().equals(produto)) {
						return venda;
					}
				}
			}
		}
		return null;
	}

	public Optional<Venda> recuperaPorId(final long id) {
		return vendaRepository.findById(id);
	}

	public void atualizarEstoqueProduto(final Venda venda, final EnumTipoMovimento tipoMvto) throws Exception {
		final List<VendaItem> itens = venda.getItens();
		if (Utils.empty(itens)) {
			throw new Exception("A venda deve ter ao menos um item vinculado");
		}
		for (VendaItem vendaItem : itens) {
			final Produto produto = vendaItem.getProduto();
			final Estoque estoque = produto.getEstoque();
			if (estoque != null) {
				estoqueService.movimentarEstoque(estoque, vendaItem.getQuantidade(), EnumTipoMovimento.SAIDA);
			}
		}
	}
}
