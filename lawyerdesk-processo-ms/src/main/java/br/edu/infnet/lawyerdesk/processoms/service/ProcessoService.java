package br.edu.infnet.lawyerdesk.processoms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lawyerdesk.processoms.dto.ProcessoDTO;
import br.edu.infnet.lawyerdesk.processoms.dto.VincularAdvogadoAProcessoDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Processo;
import br.edu.infnet.lawyerdesk.processoms.model.ProcessoAdvogado;
import br.edu.infnet.lawyerdesk.processoms.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	public List<Processo> getAll() {
		return this.processoRepository.findAll();
	}

	public Optional<Processo> getById(Long id) {
		return this.processoRepository.findById(id);
	}

	public void save(Processo processo) {
		this.processoRepository.save(processo);
	}

	public void save(ProcessoDTO dto) {

		Processo processo = new Processo();

		if (dto.getId() != null) {
			processo = this.getById(dto.getId()).orElse(new Processo());
		}

		processo.setNumero(dto.getNumero());
		processo.setStatus(dto.getStatus());

		Set<ProcessoAdvogado> advogados = new HashSet<>();

		if (dto.getIdsAdvogados() != null) {
			for (Long idAdvogado : dto.getIdsAdvogados()) {
				ProcessoAdvogado processoAdvogado = new ProcessoAdvogado();
				processoAdvogado.setAdvogadoId(idAdvogado);
				processoAdvogado.setProcesso(processo);
				advogados.add(processoAdvogado);
			}

			processo.setAdvogados(advogados);
		}

		this.processoRepository.save(processo);
	}

	public void delete(Long id) {
		this.processoRepository.deleteById(id);
	}

	public List<Processo> getByAdvogadoId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void vincularAdvogadoAProcesso(VincularAdvogadoAProcessoDTO dto) throws Exception {
		Processo processo = this.processoRepository.findByNumero(dto.getNumProcesso()).orElseThrow(()-> new Exception("Nenhum processo com o número informado foi encontrado"));
		ProcessoAdvogado processoAdvogado = new ProcessoAdvogado();
		processoAdvogado.setAdvogadoId(dto.getIdAdvogado());
		processoAdvogado.setProcesso(processo);
		processo.addAdvogadosAoProcesso(processoAdvogado);
		this.processoRepository.save(processo);
	}

}
