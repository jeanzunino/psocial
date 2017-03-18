package br.com.jvzsolutions.rp.dao;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Cidade;
import br.com.jvzsolutions.rp.model.Estabelecimento;
import br.com.jvzsolutions.rp.model.Estado;
import br.com.jvzsolutions.rp.model.PrecoAtual;
import br.com.jvzsolutions.rp.model.PrecoAtualPK;
import br.com.jvzsolutions.rp.model.Produto;
import br.com.jvzsolutions.rp.model.Usuario;

public class PrecoAtualDAOTest {

	@Test
	public void saveTest() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance("psocial");
		
		PrecoAtual u = new PrecoAtual();
		u.setValor(156);
		

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
		
		Produto produto = new Produto();
		produto.setId(1L);
		
		PrecoAtualPK pk = new PrecoAtualPK();
		pk.setEstabelecimento(estabelecimento);
		pk.setProduto(produto);
	
		u.setPrecoAtualPK(pk);
		Usuario usuario = new Usuario();
		usuario.setId(8L);
		u.setUsuario(usuario);
		u.setDataAtualizacao(new Date(System.currentTimeMillis()));
		
		IPersistenceOperationsDAO<PrecoAtual> dao = (IPersistenceOperationsDAO<PrecoAtual>) instance.createDAO(u.getClass());
		Assert.assertNotNull(dao);
		
		dao.save(u);
		
	}
}
