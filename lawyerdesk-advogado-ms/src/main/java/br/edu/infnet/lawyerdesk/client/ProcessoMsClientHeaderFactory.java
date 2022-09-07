package br.edu.infnet.lawyerdesk.client;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.edu.infnet.lawyerdesk.model.dto.ResponseDTO;
import br.edu.infnet.lawyerdesk.model.dto.UsuarioDTO;

@ApplicationScoped
public class ProcessoMsClientHeaderFactory implements ClientHeadersFactory {

	@Inject
	@RestClient
	private AuthMsClient authClient;

	@Override
	public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
			MultivaluedMap<String, String> clientOutgoingHeaders) {
		MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
		result.add("X-request-uuid", UUID.randomUUID().toString());

		ResponseDTO dto = authClient.login(new UsuarioDTO("talesb_adv_admin", "12345"));
		String token = dto.getMessage();

		result.add("Authorization", "Bearer ".concat(token));
		return result;
	}
}
