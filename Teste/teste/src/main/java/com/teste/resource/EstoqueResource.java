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

import com.teste.model.Estoque;
import com.teste.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueResource {

	@Autowired
	private EstoqueService estoqueService;

	@PostMapping("/estoques")
	public Estoque salvar(@RequestBody @Valid Estoque estoque) {
		return estoqueService.salvar(estoque);
	}

	@DeleteMapping("/estoques/{id}")
	public ResponseEntity<?> excuir(@PathVariable final long id) {

		Optional<Estoque> estoque = estoqueService.recuperaPorId(id);
		if (estoque.isPresent()) {
			try {
				estoqueService.remover(estoque.get());
				return ResponseEntity.ok(estoque);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/estoques/{id}")
	public ResponseEntity<?> recuperaPorId(@PathVariable final long id) {

		Optional<Estoque> estoque = estoqueService.recuperaPorId(id);
		if (estoque.isPresent()) {
			return ResponseEntity.ok(estoque);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/estoques")
	public List<Estoque> listarEstoque() {
		return estoqueService.listarTodos();
	}
}
