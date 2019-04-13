package com.teste.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.dao.ProdutoRepository;
import com.teste.model.Produto;
import com.teste.model.Venda;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

    @Autowired
	private VendaService vendaService;

	public List<Produto> listarTodos() {
		final Iterable<Produto> produtos = produtoRepository.findAll();
		List<Produto> produtosList = new ArrayList<>();
		if (produtos != null) {
			for (Produto produto : produtos) {
				produtosList.add(produto);
			}
			return produtosList;
		}
		return null;
	}

	public Produto salvar(final Produto produto) {
		return produtoRepository.save(produto);
	}

	public void remover(final Produto produto) throws Exception {
		if (this.getVendaPorProduto(produto) != null) {
			produtoRepository.delete(produto);
			return;
		}
		throw new Exception("Produto vinculado à uma venda não pode ser removido");

	}

	public Venda getVendaPorProduto(final Produto produto) {
		return vendaService.getVendaPorProduto(produto);
	}

	public Optional<Produto> recuperaPorId(final long id) {
		return produtoRepository.findById(id);
	}

}
