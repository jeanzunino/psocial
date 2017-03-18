package br.com.jvzsolutions.rp.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.CategoriaProduto;

@Path("/categoriasProdutos")
public class CategoriaProdutoService extends AbstractService<CategoriaProduto>{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<CategoriaProduto>> geCategoriaProduto() throws Throwable {
		return getAll(CategoriaProduto.class);
	}
	
	@POST                                            
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JResponse addNew(CategoriaProduto produto) throws Throwable {
		IPersistenceOperationsDAO<CategoriaProduto> daoProduto = getDao(CategoriaProduto.class);
		daoProduto.save(produto);
		return JResponse.ok().build();
	}
	
	@PUT                                     
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JResponse update(CategoriaProduto produto) throws Throwable {
		IPersistenceOperationsDAO<CategoriaProduto> daoProduto = getDao(CategoriaProduto.class);
		daoProduto.update(produto);
		return JResponse.ok().build();
	}
}
