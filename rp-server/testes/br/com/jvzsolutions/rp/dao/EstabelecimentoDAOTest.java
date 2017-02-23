package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Estabelecimento;

public class EstabelecimentoDAOTest {

	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		Estabelecimento u = new Estabelecimento();
		u.setId(1L);
		u.setNome("TOP Supermercados");
		u.setEndereco("R. Johann Sachse, 2476");
		IPersistenceOperationsDAO<Estabelecimento> dao = (IPersistenceOperationsDAO<Estabelecimento>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
	}
}
