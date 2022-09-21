package br.edu.infnet.lawyerdesk.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.edu.infnet.lawyerdesk.client.ProcessoMsClient;
import br.edu.infnet.lawyerdesk.model.Advogado;
import br.edu.infnet.lawyerdesk.model.dto.CadastroProcessoDTO;
import br.edu.infnet.lawyerdesk.model.dto.ClienteDTO;
import br.edu.infnet.lawyerdesk.model.dto.ProcessoDTO;
import br.edu.infnet.lawyerdesk.model.dto.VincularAdvogadoAProcessoDTO;
import br.edu.infnet.lawyerdesk.repository.AdvogadoRepository;

@RequestScoped
public class AdvogadoService {

	@Inject
	@RestClient
	private ProcessoMsClient processoClient;
	
	@Inject
	private AdvogadoRepository advogadoRepository;

	public List<Advogado> findAll() {
		return (List<Advogado>) this.advogadoRepository.findAll();
	}

	public void persist(Advogado advogado) {
		this.advogadoRepository.save(advogado);
	}

	public Advogado findById(Long id) {
		return this.advogadoRepository.findById(id).orElse(null);
	}

	public Advogado update(Long id, Advogado advogadoParam) {
		Advogado adv = this.advogadoRepository.findById(id).orElse(null);
		adv.setNome(advogadoParam.getNome());
		adv.setCpf(advogadoParam.getCpf());
		adv.setOab(advogadoParam.getOab());
		adv.setEspecialidades(advogadoParam.getEspecialidades());
		this.advogadoRepository.save(adv);
		return adv;
	}

	public void deleteById(Long id) {
		this.advogadoRepository.deleteById(id);
	}

	public void cadastrarProcesso(CadastroProcessoDTO dto) throws Exception {

		Advogado adv = this.advogadoRepository.findByOab(dto.getOab())
				.orElseThrow(() -> new Exception("Não foi encontrado nenhum adovado com a oab informada"));
		
		VincularAdvogadoAProcessoDTO dtoVinculo = new VincularAdvogadoAProcessoDTO();
		dtoVinculo.setIdAdvogado(adv.getId());
		dtoVinculo.setNumProcesso(dto.getNumeroProcesso());
		 
		this.processoClient.vincularAdvogadoAProcesso(dtoVinculo);

	}

	public List<ProcessoDTO> findProcessoByAdvId(Long id) {
		return this.processoClient.findProcessoByAdvId(id);
	}

	public List<ClienteDTO> findClientesByAdvId(Long id) {
		return this.processoClient.findClientesByAdvId(id);
	}

}
