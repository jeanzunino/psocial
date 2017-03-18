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
@Table(name = "produtos")
@NamedQueries({
	@NamedQuery(name = "Produto.searchById", query = "select obj from Produto obj where obj.id = ?1"),
	@NamedQuery(name = "Produto.search", query = "select obj from Produto obj where LOWER(obj.nome) like ?1 or obj.codigoBarras = ?2 or LOWER(obj.categoria.nome) like ?3"),
	@NamedQuery(name = "Produto.searchByCodigoBarras", query = "select obj from Produto obj where obj.codigoBarras = ?1")})
public class Produto implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produto_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@Column(name = "codigo_barras", nullable = true, unique = true)
	private long codigoBarras;

	@ManyToOne
	@JoinColumn(name = "categoria", nullable = false)
	private CategoriaProduto categoria;
	
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

	public long getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(long codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}
	
	
	
}
