package br.com.jvzsolutions.rp.dao;

import java.util.List;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.AbstractDAO;
import br.com.jvzsolutions.rp.model.Usuario;

public class UsuarioDAO extends AbstractDAO<Usuario> {

	public UsuarioDAO(String puName) {
		super(puName);
	}

	public Usuario getByEmail(String email) throws ExcecaoGenericaDAO {

		Object[] parameters = new Object[] { email };
		List<Usuario> result = executeNamedQuery("Usuario.searchByEmail", parameters);
		if (result.isEmpty()) {
			return null;
		}
		Usuario u = result.get(0);
		return u;
	}
}
