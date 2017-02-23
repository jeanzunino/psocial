package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Usuario;

public class UsuarioDAOTest {
	
	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		Usuario u = new Usuario();
		u.setId(1L);
		u.setNome("jasdlkas");
		u.setEmail("kasjdhkajsdh");
		IPersistenceOperationsDAO<Usuario> dao = (IPersistenceOperationsDAO<Usuario>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
	}
}
