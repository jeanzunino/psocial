package br.com.jvzsolutions.rp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IEntity;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.PrecoAtual;
import br.com.jvzsolutions.rp.model.Produto;

@Path("/produtos")
public class ProdutoService extends AbstractService<Produto>{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Produto>> getProdutos() throws Throwable {
		return getAll(Produto.class);
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Produto>> getProdutos(@PathParam("id") int id) throws Throwable {
		return getById(Produto.class,id);
	}
	
	@GET
	@Path("{produto}/precos")
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse getProdutos(@PathParam("codigoBarras") long codigoBarras) throws Throwable {
		IPersistenceOperationsDAO<Produto> daoProduto = (IPersistenceOperationsDAO<Produto>) DAOFactory.getInstance(ConfigConstants.puName).createDAO(Produto.class);
		Object[] parameters = new Object[]{codigoBarras};
		List<Produto> produtoList = daoProduto.executeNamedQuery("Produto.searchByCodigoBarras", parameters);
		if(produtoList.isEmpty()){
			return JResponse.status(Status.NOT_FOUND).build();
		}
		
		Produto p = produtoList.get(0);
		parameters = new Object[]{p};
		IPersistenceOperationsDAO<PrecoAtual> daoPrecos = (IPersistenceOperationsDAO<PrecoAtual>) DAOFactory.getInstance(ConfigConstants.puName).createDAO(PrecoAtual.class);
		List<PrecoAtual> precos = daoPrecos.executeNamedQuery("PrecoAtual.searchByProduto", parameters);
		return JResponse.ok(precos).build();
	}
}
