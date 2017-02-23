package br.com.jvzsolutions.rp.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.model.Estado;

@Path("/estados")
public class EstadoService extends AbstractService<Estado>{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Estado>> getEstados() throws Throwable {
		return getAll(Estado.class);
	}
}
