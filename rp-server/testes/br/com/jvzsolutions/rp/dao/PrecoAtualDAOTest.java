package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.PrecoAtual;

public class PrecoAtualDAOTest {

	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		PrecoAtual u = new PrecoAtual();
		u.setValor(156);
		
		IPersistenceOperationsDAO<PrecoAtual> dao = (IPersistenceOperationsDAO<PrecoAtual>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
		
	}
}
