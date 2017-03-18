package br.com.jvzsolutions.rp.dao;

import java.util.List;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.AbstractDAO;
import br.com.jvzsolutions.rp.model.CategoriaProduto;

public class CategoriaProdutoDAO extends AbstractDAO<CategoriaProduto>{

	public CategoriaProdutoDAO(String puName) {
		super(puName);
	}
	
	public List<CategoriaProduto> getByNome(String nome) throws ExcecaoGenericaDAO {
		Object[] parameters = new Object[] { "%"+nome.toLowerCase()+"%" };
		List<CategoriaProduto> result = executeNamedQuery("CategoriaProduto.search", parameters);
		return result;
	}
}
