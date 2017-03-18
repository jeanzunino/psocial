package br.com.jvsolutions.rp.facade;

import java.util.List;

import br.com.jvzsolutions.rp.dao.CategoriaProdutoDAO;
import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.CategoriaProduto;
import br.com.jvzsolutions.rp.model.Produto;
import br.com.jvzsolutions.rp.services.ConfigConstants;

public final class RPFacade {

	private static DAOFactory getDaoFactory() throws ExcecaoGenericaDAO {
		DAOFactory instance = DAOFactory.getInstance(ConfigConstants.puName);
		return instance;
	}

	public static List<CategoriaProduto> buscarCategoria(String nome) throws ExcecaoGenericaDAO {
		DAOFactory daoFactory = getDaoFactory();
		CategoriaProdutoDAO dao = (CategoriaProdutoDAO) daoFactory
				.createDAO(CategoriaProduto.class);
		List<CategoriaProduto> result = dao.getByNome(nome);
		return result;
	}
	
	public static void cadastrarProduto(Produto produto) throws ExcecaoGenericaDAO {
		DAOFactory daoFactory = getDaoFactory();
		IPersistenceOperationsDAO<Produto> dao = (IPersistenceOperationsDAO<Produto>) daoFactory
				.createDAO(Produto.class);
		Object[] parameters = new Object[] { produto.getCodigoBarras() };
		List<Produto> result = dao.executeNamedQuery("Produto.searchByCodigoBarras", parameters);
		if(!result.isEmpty()){
			throw new IllegalArgumentException("Produto já cadastrado com código de barras "+ produto.getCodigoBarras());
		}
		dao.save(produto);
	}
}
