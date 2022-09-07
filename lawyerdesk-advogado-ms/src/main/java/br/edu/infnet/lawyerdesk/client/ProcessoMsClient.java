package br.edu.infnet.lawyerdesk.client;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.edu.infnet.lawyerdesk.model.dto.VincularAdvogadoAProcessoDTO;

@Path("/processo")
@RegisterRestClient
@RegisterClientHeaders(ProcessoMsClientHeaderFactory.class)
public interface ProcessoMsClient {
	 
	@POST
	@Path("/vincularadv")
	public void vincularAdvogadoAProcesso(VincularAdvogadoAProcessoDTO dtoVinculo) ;

}
