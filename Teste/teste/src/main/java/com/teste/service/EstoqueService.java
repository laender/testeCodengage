package com.teste.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.dao.EstoqueRepository;
import com.teste.dao.MovimentoEstoqueRepository;
import com.teste.enums.EnumTipoMovimento;
import com.teste.model.Estoque;
import com.teste.model.MovimentoEstoque;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private MovimentoEstoqueRepository movimentoEstoqueRepository;

	public List<Estoque> listarTodos() {
		final Iterable<Estoque> estoques = estoqueRepository.findAll();
		List<Estoque> estoqueList = new ArrayList<>();
		if (estoques != null) {
			for (Estoque estoque : estoques) {
				estoqueList.add(estoque);
			}
			return estoqueList;
		}
		return null;
	}

	public Estoque salvar(final Estoque estoque) {
		final MovimentoEstoque movimentoEstoque = new MovimentoEstoque(estoque, EnumTipoMovimento.ENTRADA,
				                                                        estoque.getQuantidade());
		final MovimentoEstoque movimentoBd = movimentoEstoqueRepository.save(movimentoEstoque);

		return (movimentoBd.getEstoque());
	}

	public void remover(final Estoque estoque) throws Exception {
		estoqueRepository.delete(estoque);

	}

	public Optional<Estoque> recuperaPorId(final long id) {
		return estoqueRepository.findById(id);
	}

	public void movimentarEstoque(final Estoque estoque, final BigDecimal qtd, 
			final EnumTipoMovimento tipoMovimento) throws Exception {
		if (estoque == null) {
			throw new Exception("Estoque não informado");
		}

		BigDecimal qtdEstoque = estoque.getQuantidade();
		if (tipoMovimento.equals(EnumTipoMovimento.SAIDA)) {
			estoque.setQuantidade(qtdEstoque.subtract(qtd));
		} else {
			estoque.setQuantidade(qtdEstoque.add(qtd));
		}

		final MovimentoEstoque movimentoEstoque = new MovimentoEstoque(estoque, tipoMovimento, qtd);
		movimentoEstoqueRepository.save(movimentoEstoque);

	}
}
