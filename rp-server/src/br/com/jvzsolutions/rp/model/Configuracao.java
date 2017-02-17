package br.com.jvzsolutions.rp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.jvzsolutions.rp.dao.persistence.IEntity;

@Entity
@Table(name = "configuracoes")
public class Configuracao implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "configuracao_id", nullable = false, unique = true)
	private Integer id;
	
	private int versaoEstados;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVersaoEstados() {
		return versaoEstados;
	}

	public void setVersaoEstados(int versaoEstados) {
		this.versaoEstados = versaoEstados;
	}
	
}
