package br.edu.infnet.lawyerdesk.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.edu.infnet.lawyerdesk.model.Usuario;
import br.edu.infnet.lawyerdesk.model.dto.ResponseDTO;
import br.edu.infnet.lawyerdesk.model.dto.UsuarioDTO;
import br.edu.infnet.lawyerdesk.security.PasswordEncoder;
import br.edu.infnet.lawyerdesk.security.TokenUtils;
import br.edu.infnet.lawyerdesk.service.UsuarioService;

@Path("/usuario")
public class AuthResource {

	@Inject
	PasswordEncoder passwordEncoder;
	
	@Inject
	UsuarioService usuarioService;

	@ConfigProperty(name = "br.edu.infnet.lawyerdesk.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

	@PermitAll
	@POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
	public Response login(UsuarioDTO usuarioDto) {
		
		Usuario u = usuarioService.findByLogin(usuarioDto.getLogin());
		
		if (u != null && u.getSenha().equals(passwordEncoder.encode(usuarioDto.getSenha()))) {
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
