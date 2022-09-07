package br.edu.infnet.lawyerdesk.client;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.edu.infnet.lawyerdesk.model.dto.ResponseDTO;
import br.edu.infnet.lawyerdesk.model.dto.UsuarioDTO;

@Path("/auth")
@RegisterRestClient
public interface AuthMsClient {

	@POST
	@Path("/login")
	public ResponseDTO login(UsuarioDTO usuario);

}
