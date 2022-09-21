package br.edu.infnet.lawyerdesk.client;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import br.edu.infnet.lawyerdesk.model.dto.ClienteDTO;
import br.edu.infnet.lawyerdesk.model.dto.ProcessoDTO;
import br.edu.infnet.lawyerdesk.model.dto.VincularAdvogadoAProcessoDTO;

@RegisterRestClient
@RegisterClientHeaders(ProcessoMsClientHeaderFactory.class)
public interface ProcessoMsClient {
	 
	@POST
	@Path("/processo/vincularadv")
	public void vincularAdvogadoAProcesso(VincularAdvogadoAProcessoDTO dtoVinculo) ;
	
	@GET
	@Path("/processo/advogado/{id}")
	public List<ProcessoDTO> findProcessoByAdvId(@PathParam Long id) ;
	
	@GET
	@Path("/cliente/advogado/{id}")
	public List<ClienteDTO> findClientesByAdvId(@PathParam Long id) ;

}
