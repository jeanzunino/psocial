package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.CategoriaProduto;

public class CategoriaProdutoDAOTest {
	
	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		CategoriaProduto u = new CategoriaProduto();
		u.setId(2L);
		u.setNome("Comidas");
		IPersistenceOperationsDAO<CategoriaProduto> dao = (IPersistenceOperationsDAO<CategoriaProduto>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
	}
}
