package com.teste.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.model.Venda;
import com.teste.model.VendaItem;
import com.teste.service.VendaService;
import com.teste.utils.Utils;

@RestController
@RequestMapping("/venda")
public class VendaResource {

	@Autowired
	private VendaService vendaService;

	@PostMapping("/vendas")
	public ResponseEntity<?> salvar(@RequestBody @Valid final Venda venda) {
		try {
			return ResponseEntity.ok(vendaService.salvar(venda));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@DeleteMapping("/vendas/{id}")
	public ResponseEntity<?> excluir(final long id) {
		final Optional<Venda> venda = vendaService.recuperaPorId(id);
		if (venda.isPresent()) {
			try {
				vendaService.remover(venda.get());
				return ResponseEntity.ok(venda);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().build();
			}

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/vendas/{id}")
	public ResponseEntity<?> getVendaPorId(@PathVariable final long id) {

		final Optional<Venda> venda = vendaService.recuperaPorId(id);
		if (venda.isPresent()) {
			return ResponseEntity.ok(venda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/vendas")
	public List<Venda> listarVendas() {
		return vendaService.listarTodos();
	}

	@GetMapping("/vendas{id}/itens")
	public List<VendaItem> listarItensVenda(@PathVariable final long id) {

		try {
			final Venda venda = vendaService.recuperaPorId(id)
					.orElseThrow(() -> new Exception("Registro não encontrado"));
			final List<VendaItem> itens = venda.getItens();
			if (!Utils.empty(itens)) {
				return itens;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
