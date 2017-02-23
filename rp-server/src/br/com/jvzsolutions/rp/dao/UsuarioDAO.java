package br.com.jvzsolutions.rp.dao;

import br.com.jvzsolutions.rp.dao.persistence.AbstractDAO;
import br.com.jvzsolutions.rp.model.Usuario;

public class UsuarioDAO extends AbstractDAO<Usuario>{

	public UsuarioDAO(String puName) {
		super(puName);
	}


}
