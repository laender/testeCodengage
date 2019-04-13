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

import com.teste.model.Produto;
import com.teste.model.Venda;
import com.teste.model.VendaItem;
import com.teste.service.VendaService;
import com.teste.utils.Utils;

@RestController
@RequestMapping("/venda")
public class VendaResource  {
	
    @Autowired
	private VendaService vendaService;
	
	
	
	@PostMapping("/vendas")
	public Venda salvar(@RequestBody @Valid final Venda venda) {
		return vendaService.salvar(venda);
	}

	@DeleteMapping("/vendas/{id}")
	public String excluir(final long id){
		
		try {
			final Venda venda = vendaService.recuperaPorId(id).orElseThrow(() -> new Exception("Registro não encontrado"));
    		vendaService.remover(venda);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@GetMapping("/vendas/{id}")
	public ResponseEntity<?> getVendaPorId(@PathVariable final long id) {

		Optional<Venda> venda = vendaService.recuperaPorId(id);
		if(venda.isPresent()) {
			return ResponseEntity.ok(venda);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@GetMapping("/vendas")
	public List<Venda> listarVendas(){
		return vendaService.listarTodos();
	}
	
	@GetMapping("/vendas{id}/itens")
	public List<VendaItem> listarItensVenda(@PathVariable final long id){
		
		try {
			final Venda venda =  vendaService.recuperaPorId(id)
				.orElseThrow(() -> new Exception("Registro não encontrado"));
			List<VendaItem> itens = venda.getItens();
			if(!Utils.empty(itens)) {
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
