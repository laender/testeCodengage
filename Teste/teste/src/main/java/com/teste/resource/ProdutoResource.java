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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.model.Produto;
import com.teste.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoResource  {
	
	@Autowired
	private ProdutoService produtoService;


	@PostMapping("/produtos")
	public Produto salvar(@RequestBody @Valid Produto produto) {
		return produtoService.salvar(produto);
	}
	
	@PutMapping("/produtos")
	public Produto atualizar(@RequestBody @Valid final Produto produto) {
		return produtoService.salvar(produto);
	}

	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<?> excuir(@PathVariable final long id){
		Optional<Produto> produto = produtoService.recuperaPorId(id);
		if(produto.isPresent()) {
			try {
				produtoService.remover(produto.get());
				return ResponseEntity.ok(produto);
			} catch (Exception e) {
				return ResponseEntity.unprocessableEntity().build();
			}
			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

   @GetMapping("/produtos/{id}")
	public ResponseEntity<?> recuperaPorId(@PathVariable final long id){
		
			Optional<Produto> produto = produtoService.recuperaPorId(id);
			if(produto.isPresent()) {
				return ResponseEntity.ok(produto);
			}
			else {
				return ResponseEntity.notFound().build();
			}
	}


	@GetMapping("/produtos")
	public List<Produto> listarProdutos() {
		return produtoService.listarTodos();
	}
}
