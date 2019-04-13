package com.teste.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "venda")
public class Venda {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name = "nome_cliente")
	private String nomeCliente;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@NotNull
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "venda")
	private List<VendaItem> itens;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<VendaItem> getItens() {
		return itens;
	}
	public void setItens(List<VendaItem> itens) {
		this.itens = itens;
	}
	
	
	
	
}
