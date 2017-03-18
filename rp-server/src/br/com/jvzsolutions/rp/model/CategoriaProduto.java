package br.com.jvzsolutions.rp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.jvzsolutions.rp.dao.persistence.IEntity;

@Entity
@Table(name = "categorias_produtos")
@NamedQueries({
	@NamedQuery(name = "CategoriaProduto.search", query = "select obj from CategoriaProduto obj where LOWER(obj.nome) like ?1")
	})

public class CategoriaProduto implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoria_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "categoria_pai", nullable = true)
	private CategoriaProduto pai;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaProduto getPai() {
		return pai;
	}

	public void setPai(CategoriaProduto pai) {
		this.pai = pai;
	}
	
}
