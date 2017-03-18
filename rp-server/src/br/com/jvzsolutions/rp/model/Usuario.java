package br.com.jvzsolutions.rp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.jvzsolutions.rp.dao.persistence.IEntity;

@Entity
@Table(name = "usuarios")
@NamedQueries({
	@NamedQuery(name = "Usuario.searchById", query = "select obj from Usuario obj where obj.id = ?1"),
	@NamedQuery(name = "Usuario.searchByEmail", query = "select obj from Usuario obj where obj.email = ?1")})
public class Usuario implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
