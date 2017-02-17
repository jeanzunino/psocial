package br.com.jvzsolutions.rp.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;

import br.com.jvzsolutions.rp.model.Cidade;

@Path("/cidades")
public class CidadeService {

	@GET
	public Response getCidades(){
		List<Cidade> result = new ArrayList<>();
		return Response.status(Status.OK).entity(result).build();
	}
}
