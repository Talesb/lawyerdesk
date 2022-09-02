package br.edu.infnet.lawyerdesk.auth.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.edu.infnet.lawyerdesk.auth.model.Usuario;
import br.edu.infnet.lawyerdesk.auth.model.dto.ResponseDTO;
import br.edu.infnet.lawyerdesk.auth.model.dto.UsuarioDTO;
import br.edu.infnet.lawyerdesk.auth.security.PasswordEncoder;
import br.edu.infnet.lawyerdesk.auth.security.TokenUtils;

@Path("/auth")
public class AuthResource {

	@Inject
	PasswordEncoder passwordEncoder;
 

	@ConfigProperty(name = "br.edu.infnet.lawyerdesk.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

	@PermitAll
	@POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response login(UsuarioDTO dto) {
		
		Usuario u = Usuario.findByLogin(dto.getLogin());
		
		if (u != null && u.getSenha().equals(passwordEncoder.encode(dto.getSenha()))) {
			try {
				return Response.ok(new ResponseDTO(TokenUtils.generateToken(u.getLogin(), u.getRoles(), duration, issuer))).build();
			} catch (Exception e) {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
}
