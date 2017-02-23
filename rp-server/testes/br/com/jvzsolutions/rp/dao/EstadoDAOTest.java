package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Estado;

public class EstadoDAOTest {
	
	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Santa Catarina");
		IPersistenceOperationsDAO<Estado> dao = (IPersistenceOperationsDAO<Estado>) instance.createDAO(estado.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(estado);
	}
}
