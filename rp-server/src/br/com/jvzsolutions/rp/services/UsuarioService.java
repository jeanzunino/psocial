package br.com.jvzsolutions.rp.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.dao.UsuarioDAO;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.model.Cliente;
import br.com.jvzsolutions.rp.model.Usuario;

@Path("/usuarios")
public class UsuarioService extends AbstractService<Usuario>{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<List<Usuario>> getUsuarios() throws Throwable {
		return getAll(Usuario.class);
	}
	
	@GET
	@Path("/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public JResponse<Usuario> getUsuarioByEmail(@PathParam("email") String email) throws Throwable {
		UsuarioDAO dao = (UsuarioDAO) getDao(Usuario.class);
		Usuario usuario = dao.getByEmail(email);
		return JResponse.ok(usuario).build();
	}
	
	@POST                                            
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JResponse addNew(Usuario produto) throws Throwable {
		IPersistenceOperationsDAO<Usuario> daoProduto = getDao(Usuario.class);
		daoProduto.save(produto);
		return JResponse.ok().build();
	}
	
	@PUT                                     
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JResponse update(Usuario produto) throws Throwable {
		IPersistenceOperationsDAO<Usuario> daoProduto = getDao(Usuario.class);
		daoProduto.update(produto);
		return JResponse.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") long id) throws Throwable {
		IPersistenceOperationsDAO<Usuario> daoProduto = getDao(Usuario.class);
		Usuario entity = getById(Usuario.class, id);
		if(entity == null){
			throw new IllegalArgumentException("Usuário não encontrado.");
		}
		daoProduto.delete(entity);
	}
}
