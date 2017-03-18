package br.com.jvzsolutions.rp.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PrecoAtualPK implements Serializable{

	@ManyToOne
	@JoinColumn(name = "produto", nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "estabelecimento", nullable = false)
	private Estabelecimento estabelecimento;

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
	
	
	
}
