package br.com.jvzsolutions.rp.dao;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Cidade;
import br.com.jvzsolutions.rp.model.Estabelecimento;
import br.com.jvzsolutions.rp.model.Estado;

public class EstabelecimentoDAOTest {

	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Santa Catarina");
		cidade.setEstado(estado);
		cidade.setId(2L);
		cidade.setNome("Blumenau");

		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(1L);
		estabelecimento.setNome("TOP Supermercados");
		estabelecimento.setEndereco("R. Johann Sachse, 2476");
		estabelecimento.setCidade(cidade);
		estabelecimento.setEstado(estado);
		
		IPersistenceOperationsDAO<Estabelecimento> dao = (IPersistenceOperationsDAO<Estabelecimento>) instance.createDAO(estabelecimento.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(estabelecimento);
	}
}
