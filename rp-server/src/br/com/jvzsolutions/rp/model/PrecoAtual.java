package br.com.jvzsolutions.rp.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
	@NamedQuery(name = "PrecoAtual.searchByProduto", query = "select obj from PrecoAtual obj where obj.precoAtualPK.produto = ?1"),
	@NamedQuery(name = "PrecoAtual.searchByProdutoCidade", query = "select obj from PrecoAtual obj where obj.precoAtualPK.produto = ?1 and obj.precoAtualPK.estabelecimento.cidade.id IN (?2)"),
	@NamedQuery(name = "PrecoAtual.searchById", query = "select obj from PrecoAtual obj where obj.precoAtualPK.produto = ?1 and obj.precoAtualPK.estabelecimento = ?2")})
public class PrecoAtual implements IEntity {

	@EmbeddedId
	private PrecoAtualPK precoAtualPK;

	@Column(name = "valor", nullable = false)
	private double valor;

	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;

	@ManyToOne
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;

	@Override
	public Long getId() {
		return null;
	}

	@Override
	public void setId(Long id) {

	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public PrecoAtualPK getPrecoAtualPK() {
		return precoAtualPK;
	}

	public void setPrecoAtualPK(PrecoAtualPK precoAtualPK) {
		this.precoAtualPK = precoAtualPK;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
