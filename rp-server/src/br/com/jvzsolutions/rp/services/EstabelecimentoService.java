package br.com.jvzsolutions.rp.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Estabelecimento;

@Path("/estabelecimentos")
public class EstabelecimentoService extends AbstractService<Estabelecimento>{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Estabelecimento>> getEstabelecimentos(@QueryParam("cidades") String cidades) throws Throwable {
		if(cidades == null || cidades.isEmpty()){
			return getAll(Estabelecimento.class);
		}
		IPersistenceOperationsDAO<Estabelecimento> daoProduto = getDao(Estabelecimento.class);
		Object[] parameters = new Object[]{};
		List<Estabelecimento> search = daoProduto.executeQuery("SELECT obj from Estabelecimento obj where obj.cidade.id in ("+cidades+")", parameters );
		return JResponse.ok(search).build();
	}
	
	@POST                                            
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JResponse addNew(Estabelecimento produto) throws Throwable {
		IPersistenceOperationsDAO<Estabelecimento> daoProduto = getDao(Estabelecimento.class);
		daoProduto.save(produto);
		return JResponse.ok().build();
	}
	
	@PUT                                     
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JResponse update(Estabelecimento produto) throws Throwable {
		IPersistenceOperationsDAO<Estabelecimento> daoProduto = getDao(Estabelecimento.class);
		daoProduto.update(produto);
		return JResponse.ok().build();
	}
}
