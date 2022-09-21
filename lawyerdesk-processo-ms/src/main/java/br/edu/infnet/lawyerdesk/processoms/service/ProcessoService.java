package br.edu.infnet.lawyerdesk.processoms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lawyerdesk.processoms.dto.ProcessoDTO;
import br.edu.infnet.lawyerdesk.processoms.dto.VincularAdvogadoAProcessoDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Cliente;
import br.edu.infnet.lawyerdesk.processoms.model.Processo;
import br.edu.infnet.lawyerdesk.processoms.model.ProcessoAdvogado;
import br.edu.infnet.lawyerdesk.processoms.repository.ClienteRepository;
import br.edu.infnet.lawyerdesk.processoms.repository.ProcessoRepository;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Processo> getAll() {
		return this.processoRepository.findAll();
	}

	public Optional<Processo> getById(Long id) {
		return this.processoRepository.findById(id);
	}

	public void save(Processo processo) {
		this.processoRepository.save(processo);
	}

	public Processo save(ProcessoDTO dto) throws Exception {

		Processo processo = new Processo();

		if (dto.getId() != null) {
			processo = this.getById(dto.getId()).orElse(new Processo());
		}

		processo.setNumero(dto.getNumero());
		processo.setStatus(dto.getStatus());
		processo.setDescricao(dto.getDescricao());

		Set<ProcessoAdvogado> advogados = new HashSet<>();
		Set<Cliente> clientes = new HashSet<>();

		if (dto.getIdsAdvogados() != null) {
			for (Long idAdvogado : dto.getIdsAdvogados()) {
				ProcessoAdvogado processoAdvogado = new ProcessoAdvogado();
				processoAdvogado.setAdvogadoId(idAdvogado);
				processoAdvogado.setProcesso(processo);
				advogados.add(processoAdvogado);
			}

			processo.setAdvogados(advogados);
		}

		if (dto.getIdsClientes() != null) {
			for (Long idCliente : dto.getIdsClientes()) {
				Cliente clientePersistido = this.clienteRepository.findById(idCliente).orElseThrow(() -> {
					return new Exception("Não existe nenhum cliente cadastrado com o id: " + idCliente);
				});
				clientes.add(clientePersistido);
			}

			processo.setClientes(clientes);
		}

		return this.processoRepository.save(processo);
	}

	public Processo update(Long id, ProcessoDTO dto) throws Exception {

		Processo processo = this.getById(id).orElseThrow(() -> {
			return new Exception("Nenhum endereco encontrado com o id informado");
		});

		processo.setNumero(dto.getNumero());
		processo.setStatus(dto.getStatus());

		Set<ProcessoAdvogado> advogados = new HashSet<>();
		Set<Cliente> clientes = new HashSet<>();

		if (dto.getIdsAdvogados() != null) {
			for (Long idAdvogado : dto.getIdsAdvogados()) {
				ProcessoAdvogado processoAdvogado = new ProcessoAdvogado();
				processoAdvogado.setAdvogadoId(idAdvogado);
				processoAdvogado.setProcesso(processo);
				advogados.add(processoAdvogado);
			}

			processo.setAdvogados(advogados);
		}

		if (dto.getIdsClientes() != null) {
			for (Long idCliente : dto.getIdsClientes()) {
				Cliente clientePersistido = this.clienteRepository.findById(idCliente).orElseThrow(() -> {
					return new Exception("Não existe nenhum cliente cadastrado com o id: " + idCliente);
				});
				clientes.add(clientePersistido);
			}

			processo.setClientes(clientes);
		}

		return this.processoRepository.save(processo);
	}

	public void delete(Long id) {
		this.processoRepository.deleteById(id);
	}

	public List<ProcessoDTO> getByAdvogadoId(Long advId) {
		List<Processo>  processos =  this.processoRepository.findByAdv(advId);
		return processos.stream().map(processo->processo.toDTO()).collect(Collectors.toList());
	}

	public void vincularAdvogadoAProcesso(VincularAdvogadoAProcessoDTO dto) throws Exception {
		Processo processo = this.processoRepository.findByNumero(dto.getNumProcesso())
				.orElseThrow(() -> new Exception("Nenhum processo com o número informado foi encontrado"));
		ProcessoAdvogado processoAdvogado = new ProcessoAdvogado();
		processoAdvogado.setAdvogadoId(dto.getIdAdvogado());
		processoAdvogado.setProcesso(processo);
		processo.addAdvogadosAoProcesso(processoAdvogado);
		this.processoRepository.save(processo);
	}

}
