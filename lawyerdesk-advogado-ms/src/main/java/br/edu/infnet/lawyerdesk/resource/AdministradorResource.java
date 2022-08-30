package br.edu.infnet.lawyerdesk.resource;

import java.util.List;

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

import br.edu.infnet.lawyerdesk.model.Administrador;
import br.edu.infnet.lawyerdesk.service.AdministradorService;

@Path("/administrador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdministradorResource {

	@Inject
	private AdministradorService administradorService;

	@GET
	public List<Administrador> findAll() {
		return this.administradorService.findAll();
	}

	@POST
	public Response create(Administrador administrador) {
		this.administradorService.persist(administrador);
		return Response.ok(administrador).status(201).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Administrador administradorParam) {

		if (this.administradorService.findById(id) == null) {
			return Response.ok("Nenhum administrador encontrado").type(MediaType.APPLICATION_JSON_TYPE).build();
		}

		Administrador administrador = this.administradorService.update(id, administradorParam);

		return Response.ok(administrador).build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		Administrador administrador = this.administradorService.findById(id);

		if (administrador == null) {
			throw new WebApplicationException("administrador de id " + id + " não existe.", Response.Status.NOT_FOUND);
		}

		this.administradorService.deleteById(id);
		return Response.status(204).build();
	}
	
}
