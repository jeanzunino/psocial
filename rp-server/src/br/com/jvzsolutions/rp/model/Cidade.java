package br.com.jvzsolutions.rp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.jvzsolutions.rp.dao.persistence.IEntity;

@Entity
@Table(name = "cidades")
public class Cidade implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cidade_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
