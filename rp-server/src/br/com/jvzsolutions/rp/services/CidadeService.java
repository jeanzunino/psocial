package br.com.jvzsolutions.rp.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.dao.CidadeDAO;
import br.com.jvzsolutions.rp.model.Cidade;
import br.com.jvzsolutions.rp.model.Estado;

@Path("/cidades")
public class CidadeService extends AbstractService<Cidade>{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JResponse<List<Cidade>> getCidades(@QueryParam("estado") Long estado) throws Throwable {
		CidadeDAO dao = (CidadeDAO) getDao(Cidade.class);
		List<Cidade> cidades = dao.getByEstado(estado);
		return JResponse.ok(cidades).build();
	}
}
