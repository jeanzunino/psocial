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
@Table(name = "estabelecimentos")
@NamedQueries({
		@NamedQuery(name = "Estabelecimento.searchByCnpj", query = "select obj from Estabelecimento obj where obj.cnpj = ?1") })
public class Estabelecimento implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estabelecimento_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "cnpj", nullable = false, unique = true)
	private long cnpj;

	@Column(name = "endereco", nullable = false, unique = true)
	private String endereco;
	
	@ManyToOne
	@JoinColumn(name = "estado", nullable = false)
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name = "cidade", nullable = false)
	private Cidade cidade;

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

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
