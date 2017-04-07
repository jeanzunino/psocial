package br.com.jvzsolutions.rp.dao;

import br.com.jvzsolutions.rp.dao.persistence.AbstractDAO;
import br.com.jvzsolutions.rp.model.Cliente;

public class ClienteDAO extends AbstractDAO<Cliente>{

	public ClienteDAO(String puName) {
		super(puName);
	}

}
