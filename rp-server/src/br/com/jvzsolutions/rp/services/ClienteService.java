package br.com.jvzsolutions.rp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Cliente;
import br.com.jvzsolutions.rp.model.Produto;

@Path("/clientes")
public class ClienteService extends AbstractService<Cliente> {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Cliente>> getClientes(@PathParam("id") long id) throws Throwable {
		return getById(Cliente.class, id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<?> getByFilter(@QueryParam("filter") final String filters) throws Throwable {
		long time = System.currentTimeMillis();
		IPersistenceOperationsDAO<Cliente> daoProduto = getDao(Cliente.class);
		if (filters == null || filters.isEmpty()) {
			return getAll(Cliente.class);
		}
		List<Cliente> resultList = new ArrayList<>();
		String filter = "%" + filters.toLowerCase() + "%";
		Object[] parameters = new Object[] { filter };

		List<Cliente> produtoList = daoProduto.executeNamedQuery("Cliente.search", parameters);
		resultList.addAll(produtoList);
		System.out.println("getByCodigoBarras Processado em " + (System.currentTimeMillis() - time));
		return JResponse.ok(resultList).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addNew(Cliente cliente) throws Throwable {
		IPersistenceOperationsDAO<Cliente> daoProduto = getDao(Cliente.class);
		daoProduto.save(cliente);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Cliente cliente) throws Throwable {
		IPersistenceOperationsDAO<Cliente> daoProduto = getDao(Cliente.class);
		daoProduto.update(cliente);
	}

}
