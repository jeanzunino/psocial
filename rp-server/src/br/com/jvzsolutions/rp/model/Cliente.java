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
@Table(name = "clientes")
@NamedQueries({ @NamedQuery(name = "Cliente.searchById", query = "select obj from Cliente obj where obj.id = ?1"),
		@NamedQuery(name = "Cliente.search", query = "select obj from Cliente obj where LOWER(obj.nome) like ?1") })
public class Cliente implements IEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@Column(name = "telefone", nullable = false, unique = true)
	private String telefone;

	@Column(name = "categoria_cliente", nullable = false, unique = true)
	private CategoriaPessoa categoriaCliente;

	@Column(name = "cpf_cnpj", nullable = false, unique = true)
	private String cpfCnpj;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public CategoriaPessoa getCategoriaCliente() {
		return categoriaCliente;
	}

	public void setCategoriaCliente(CategoriaPessoa categoriaCliente) {
		this.categoriaCliente = categoriaCliente;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

}
