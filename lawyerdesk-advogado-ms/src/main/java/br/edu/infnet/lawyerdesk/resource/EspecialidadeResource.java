package br.edu.infnet.lawyerdesk.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
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

import br.edu.infnet.lawyerdesk.model.Especialidade;
import br.edu.infnet.lawyerdesk.service.EspecialidadeService;

@Path("/especialidade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN","ADV"})
public class EspecialidadeResource {

	@Inject
	private EspecialidadeService espacialidadeService;

	
	@GET
	public List<Especialidade> findAll() {
		return this.espacialidadeService.findAll();
	}

	@POST
	public Response create(Especialidade especialidade) {
		this.espacialidadeService.persist(especialidade);
		return Response.ok(especialidade).status(201).build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		Especialidade especialidade = this.espacialidadeService.findById(id);

		if (especialidade == null) {
			throw new WebApplicationException("Especialidade de id " + id + " não existe.", Response.Status.NOT_FOUND);
		}

		this.espacialidadeService.deleteById(id);
		return Response.status(204).build();
	}

}
