package br.com.jvzsolutions.rp.dao;

import br.com.jvzsolutions.rp.dao.persistence.AbstractDAO;
import br.com.jvzsolutions.rp.model.Produto;

public class ProdutoDAO extends AbstractDAO<Produto>{

	public ProdutoDAO(String puName) {
		super(puName);
	}

}
