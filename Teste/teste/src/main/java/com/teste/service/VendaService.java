package com.teste.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.dao.VendaRepository;
import com.teste.model.Produto;
import com.teste.model.Venda;
import com.teste.model.VendaItem;
import com.teste.utils.Utils;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	public List<Venda> listarTodos(){
		final Iterable<Venda> vendas = vendaRepository.findAll();
		List<Venda> vendasList = new ArrayList<>();
		if(vendas != null) {
			for (Venda venda : vendas) {
				vendasList.add(venda);
			}
			return vendasList;
		}
		return null;
	}
	
	public Venda salvar(final Venda venda) {
		return vendaRepository.save(venda);
	}
	
	public void remover(final Venda venda) {
		vendaRepository.delete(venda);
	}
	
	public Venda getVendaPorProduto(final Produto produto) {
		final List<Venda> vendas = this.listarTodos();
		if(!Utils.empty(vendas)) {
			for (Venda venda : vendas) {
				List<VendaItem> itens = venda.getItens();
				for (VendaItem item : itens) {
					if(item.getProduto().equals(produto)) {
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
}
