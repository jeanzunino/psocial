package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Cidade;
import br.com.jvzsolutions.rp.model.Estado;

public class CidadeDAOTest {
	
	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		Cidade u = new Cidade();
		u.setId(1L);
		u.setNome("Blumenau");
		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Santa Catarina");
		u.setEstado(estado );
		IPersistenceOperationsDAO<Cidade> dao = (IPersistenceOperationsDAO<Cidade>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
	}
}
