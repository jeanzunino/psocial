package br.com.jvzsolutions.rp.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.jvzsolutions.rp.dao.persistence.IEntity;

@Entity
@Table(name = "precos_atuais")
@NamedQueries({
	@NamedQuery(name = "PrecoAtual.searchByProduto", query = "select obj from PrecoAtual obj where obj.produto = ?1") })
public class PrecoAtual implements IEntity{

	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "estabelecimento_id", nullable = false)
	private Estabelecimento estabelecimento;
	
	@Column(name = "valor", nullable = false)
	private double valor;
	
	@Override
	public Long getId() {
		return null;
	}

	@Override
	public void setId(Long id) {
		
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
}
