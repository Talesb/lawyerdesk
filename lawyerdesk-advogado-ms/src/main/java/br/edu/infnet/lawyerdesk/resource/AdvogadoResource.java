package br.edu.infnet.lawyerdesk.resource;

import java.util.List;

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

import br.edu.infnet.lawyerdesk.model.Advogado;
import br.edu.infnet.lawyerdesk.service.AdvogadoService;

@Path("/advogado")
@RolesAllowed("ADMIN" )
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdvogadoResource {

	@Inject
	private AdvogadoService advogadoService;

	@GET
	public List<Advogado> findAll() {
		return this.advogadoService.findAll();
	}

	@POST
	public Response create(Advogado advogado) {
		this.advogadoService.persist(advogado);
		return Response.ok(advogado).status(201).build();
	}

	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Advogado advogadoParam) {

		if (this.advogadoService.findById(id) == null) {
			return Response.ok("Nenhum advogado encontrado").type(MediaType.APPLICATION_JSON_TYPE).build();
		}

		Advogado advogado = this.advogadoService.update(id, advogadoParam);

		return Response.ok(advogado).build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		Advogado advogado = this.advogadoService.findById(id);

		if (advogado == null) {
			throw new WebApplicationException("Advogado de id " + id + " não existe.", Response.Status.NOT_FOUND);
		}

		this.advogadoService.deleteById(id);
		return Response.status(204).build();
	}

}
