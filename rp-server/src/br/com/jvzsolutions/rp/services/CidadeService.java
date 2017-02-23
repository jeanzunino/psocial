package br.com.jvzsolutions.rp.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.model.Cidade;

@Path("/cidades")
public class CidadeService extends AbstractService<Cidade>{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Cidade>> getCidades() throws Throwable {
		return getAll(Cidade.class);
	}
}
