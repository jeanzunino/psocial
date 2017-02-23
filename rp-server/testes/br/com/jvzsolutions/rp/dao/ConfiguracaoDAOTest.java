package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Configuracao;

public class ConfiguracaoDAOTest {
	
	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		Configuracao u = new Configuracao();
		u.setId(1L);
		IPersistenceOperationsDAO<Configuracao> dao = (IPersistenceOperationsDAO<Configuracao>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
	}
}
