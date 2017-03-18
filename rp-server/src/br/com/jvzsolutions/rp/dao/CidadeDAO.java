package br.com.jvzsolutions.rp.dao;

import java.util.List;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.AbstractDAO;
import br.com.jvzsolutions.rp.model.Cidade;

public class CidadeDAO extends AbstractDAO<Cidade>{

	public CidadeDAO(String puName) {
		super(puName);
	}
	
	public List<Cidade> getByEstado(Long estadoId) throws ExcecaoGenericaDAO {
		Object[] parameters = new Object[] { estadoId };
		List<Cidade> result = executeNamedQuery("Cidade.searchByEstado", parameters);
		return result;
	}

}
