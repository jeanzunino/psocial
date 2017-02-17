package br.com.jvzsolutions.rp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.networkmanagerj.model.AcaoMensagem;

import br.com.jvzsolutions.rp.dao.persistence.IEntity;

@Entity
@Table(name = "estados")
public class Estado implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estado_id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@OneToMany(mappedBy = "estado_id", targetEntity = Cidade.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cidade> cidades;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	
}
