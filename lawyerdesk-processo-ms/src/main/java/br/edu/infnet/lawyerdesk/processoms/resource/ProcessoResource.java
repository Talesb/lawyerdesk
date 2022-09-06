package br.edu.infnet.lawyerdesk.processoms.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lawyerdesk.processoms.dto.ProcessoDTO;
import br.edu.infnet.lawyerdesk.processoms.dto.VincularAdvogadoAProcessoDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Processo;
import br.edu.infnet.lawyerdesk.processoms.service.ProcessoService;

@RestController
@RequestMapping("/processo")
public class ProcessoResource {

	@Autowired
	private ProcessoService processoService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Processo> getAll() {
		return this.processoService.getAll();
	}

	@GetMapping("/advogado/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Processo> getByAdvogadoId(@PathVariable Long id) {
		return this.processoService.getByAdvogadoId(id);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<Processo> getById(@PathVariable Long id) {
		return this.processoService.getById(id);
	}

	@PostMapping("/vincularadv")
	@PreAuthorize("hasRole('ADMIN')")
	public void vincularAdvogado(@RequestBody VincularAdvogadoAProcessoDTO dto) {
		this.processoService.vincularAdvogadoAProcesso(dto);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public void save(@RequestBody ProcessoDTO dto) {
		this.processoService.save(dto);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public void update(@RequestBody ProcessoDTO dto) {
		this.processoService.save(dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteById(@PathVariable Long id) {
		this.processoService.delete(id);
	}

}
