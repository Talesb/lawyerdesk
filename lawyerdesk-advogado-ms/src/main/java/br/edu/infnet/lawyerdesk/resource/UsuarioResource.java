package br.edu.infnet.lawyerdesk.resource;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.infnet.lawyerdesk.model.Usuario;
import br.edu.infnet.lawyerdesk.service.UsuarioService;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

	
	@Inject
	private UsuarioService usuarioService;

	@RolesAllowed("ADMIN" )
	@GET
	public List<Usuario> findAll() {
		return this.usuarioService.findAll();
	}

	@PermitAll
	@POST
	public Response create(Usuario usuario) {
		this.usuarioService.persist(usuario);
		return Response.ok(usuario).status(201).build();
	}

	@RolesAllowed("ADMIN" )
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Usuario usuarioParam) {

		if (this.usuarioService.findById(id) == null) {
			return Response.ok("Nenhum usuario encontrado").type(MediaType.APPLICATION_JSON_TYPE).build();
		}

		Usuario usuario = this.usuarioService.update(id, usuarioParam);

		return Response.ok(usuario).build();
	}

	@RolesAllowed("ADMIN" )
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		Usuario usuario = this.usuarioService.findById(id);

		if (usuario == null) {
			throw new WebApplicationException("Usuario de id " + id + " não existe.", Response.Status.NOT_FOUND);
		}

		this.usuarioService.deleteById(id);
		return Response.status(204).build();
	}
	
	
	
}
