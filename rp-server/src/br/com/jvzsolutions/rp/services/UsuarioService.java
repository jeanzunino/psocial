package br.com.jvzsolutions.rp.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.model.Usuario;

@Path("/usuarios")
public class UsuarioService extends AbstractService<Usuario>{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Usuario>> getProdutos() throws Throwable {
		return getAll(Usuario.class);
	}
}
