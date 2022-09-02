package br.edu.infnet.lawyerdesk.auth.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.infnet.lawyerdesk.auth.model.Role;
import br.edu.infnet.lawyerdesk.auth.service.RoleService;

@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

	@Inject
	private RoleService roleService;

	@RolesAllowed("ADMIN" )
	@GET
	public List<Role> findAll() {
		return this.roleService.findAll();
	}

	@RolesAllowed("ADMIN" )
	@POST
	public Response create(Role role) {
		this.roleService.persist(role);
		return Response.ok(role).status(201).build();
	}

	@RolesAllowed("ADMIN" )
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		Role role = this.roleService.findById(id);

		if (role == null) {
			throw new WebApplicationException("Role de id " + id + " não existe.", Response.Status.NOT_FOUND);
		}

		this.roleService.deleteById(id);
		return Response.status(204).build();
	}

}
