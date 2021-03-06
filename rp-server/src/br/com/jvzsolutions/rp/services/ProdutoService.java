package br.com.jvzsolutions.rp.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Cliente;
import br.com.jvzsolutions.rp.model.PrecoAtual;
import br.com.jvzsolutions.rp.model.Produto;
import br.com.jvzsolutions.rp.model.Usuario;
import br.com.jvzsolutions.rp.services.model.StringListModel;

@Path("/produtos")
public class ProdutoService extends AbstractService<Produto> {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Produto>> getProdutos(@PathParam("id") long id) throws Throwable {
		Produto entity = getById(Produto.class, id);
		if(entity == null){
			throw new IllegalArgumentException("Produto n�o encontrado.");
		}
		return JResponse.ok(Arrays.asList(entity)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<?> getByCodigoBarras(@QueryParam("filter") final List<String> filters) throws Throwable {
		long time = System.currentTimeMillis();
		IPersistenceOperationsDAO<Produto> daoProduto = getDao(Produto.class);
		if (filters == null || filters.isEmpty()) {
			return getAll(Produto.class);
		}
		List<Produto> resultList = new ArrayList<>();
		long barcode = -1;
		for (String filter : filters) {

			try {
				barcode = Long.parseLong(filter);
			} catch (NumberFormatException e) {
				// OK
			}
			filter = "%" + filter.toLowerCase() + "%";
			Object[] parameters = new Object[] { filter, barcode, filter };

			List<Produto> produtoList = daoProduto.executeNamedQuery("Produto.search", parameters);
			resultList.addAll(produtoList);
		}
		System.out.println(new Date() + " getByCodigoBarras Processado em " + (System.currentTimeMillis() - time));
		return JResponse.ok(resultList).build();
	}

	@GET
	@Path("/{codigoBarras}/precos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JResponse<List<PrecoAtual>> getPrecos(@PathParam("codigoBarras") long codigoBarras,
			@QueryParam("cidades") String cidades) throws Throwable {
		long time = System.currentTimeMillis();
		IPersistenceOperationsDAO<Produto> daoProduto = getDao(Produto.class);
		Object[] parameters = new Object[] { codigoBarras };
		List<Produto> produtoList = daoProduto.executeNamedQuery("Produto.searchByCodigoBarras", parameters);
		if (produtoList.isEmpty()) {
			throw new IllegalArgumentException("produto n�o cadastrado");
		}

		IPersistenceOperationsDAO<PrecoAtual> daoPrecos = (IPersistenceOperationsDAO<PrecoAtual>) DAOFactory
				.getInstance(ConfigConstants.puName).createDAO(PrecoAtual.class);

		Produto p = produtoList.get(0);
		if (cidades == null || cidades.isEmpty()) {
			parameters = new Object[] { p };

			List<PrecoAtual> precos = daoPrecos.executeNamedQuery("PrecoAtual.searchByProduto", parameters);
			System.out.println(new Date() + " getPrecos Processado em " + (System.currentTimeMillis() - time));
			return JResponse.ok(precos).build();
		}
		parameters = new Object[] { p };

		List<PrecoAtual> precos = daoPrecos.executeQuery(
				"select obj from PrecoAtual obj where obj.precoAtualPK.produto = ?1 and obj.precoAtualPK.estabelecimento.cidade.id IN ("
						+ cidades + ")",
				parameters);
		System.out.println(new Date() + " getPrecos Processado em " + (System.currentTimeMillis() - time));
		return JResponse.ok(precos).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addNew(Produto produto) throws Throwable {
		IPersistenceOperationsDAO<Produto> daoProduto = getDao(Produto.class);
		daoProduto.save(produto);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws Throwable {
		IPersistenceOperationsDAO<Produto> daoProduto = getDao(Produto.class);
		Produto entity = getById(Produto.class, id);
		if(entity == null){
			throw new IllegalArgumentException("Produto n�o encontrado.");
		}
		daoProduto.delete(entity);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Produto produto) throws Throwable {
		IPersistenceOperationsDAO<Produto> daoProduto = getDao(Produto.class);
		daoProduto.update(produto);
	}

	@PUT
	@Path("/{codigoBarras}/precos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(PrecoAtual preco) throws Throwable {
		IPersistenceOperationsDAO<PrecoAtual> daoPrecos = (IPersistenceOperationsDAO<PrecoAtual>) DAOFactory
				.getInstance(ConfigConstants.puName).createDAO(PrecoAtual.class);
		Object[] parameters = new Object[] { preco.getPrecoAtualPK().getProduto(),
				preco.getPrecoAtualPK().getEstabelecimento() };
		List<PrecoAtual> precos = daoPrecos.executeNamedQuery("PrecoAtual.searchById", parameters);

		IPersistenceOperationsDAO<Usuario> daoUsuario = (IPersistenceOperationsDAO<Usuario>) DAOFactory
				.getInstance(ConfigConstants.puName).createDAO(Usuario.class);

		Object[] parameterEmail = new Object[] { preco.getUsuario().getEmail() };
		List<Usuario> usuarios = daoUsuario.executeNamedQuery("Usuario.searchByEmail", parameterEmail);
		if (usuarios.isEmpty()) {
			throw new IllegalArgumentException("Usu�rio n�o cadastrado.");
		}

		Usuario usuario = usuarios.get(0);
		preco.setUsuario(usuario);
		if (precos.isEmpty()) {
			daoPrecos.save(preco);
		} else {
			daoPrecos.update(preco);
		}
	}
}
