package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.CategoriaProduto;
import br.com.jvzsolutions.rp.model.Produto;

public class ProdutoDAOTest {

	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		Produto u = new Produto();
		u.setId(1L);
		u.setNome("jasdlkas");
		
		CategoriaProduto c = new CategoriaProduto();
		c.setId(1L);
		c.setNome("Bebidas");
		u.setCategoria(c);
		IPersistenceOperationsDAO<Produto> dao = (IPersistenceOperationsDAO<Produto>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
		
	}
}
