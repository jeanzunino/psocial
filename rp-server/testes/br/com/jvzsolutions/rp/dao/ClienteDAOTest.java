package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.CategoriaPessoa;
import br.com.jvzsolutions.rp.model.Cidade;
import br.com.jvzsolutions.rp.model.Cliente;
import br.com.jvzsolutions.rp.model.Estado;

public class ClienteDAOTest {
	
	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		Cliente u = new Cliente();
		u.setNome("José Maria");
		u.setCategoriaCliente(CategoriaPessoa.JURIDICA);
		u.setCpfCnpj("132132138");
		u.setTelefone("33344118");
		IPersistenceOperationsDAO<Cliente> dao = (IPersistenceOperationsDAO<Cliente>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
	}
}
