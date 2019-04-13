package com.teste.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.teste.enums.EnumTipoMovimento;

@Entity
@Table(name = "movimento_estoque")
public class MovimentoEstoque implements Serializable {

	private static final long serialVersionUID = -7583386173219846088L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@JoinColumn(name = "id_estoque", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Estoque estoque;

	@NotNull
	@Column(name = "tipo_movimento")
	@Enumerated(EnumType.STRING)
	private EnumTipoMovimento tipoMovimento;

	@NotNull
	private BigDecimal quantidade;

	public MovimentoEstoque(@NotNull Estoque estoque, @NotNull EnumTipoMovimento tipoMovimento,
			@NotNull BigDecimal quantidade) {
		super();
		this.estoque = estoque;
		this.tipoMovimento = tipoMovimento;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public EnumTipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(EnumTipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
