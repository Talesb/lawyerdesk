package br.edu.infnet.lawyerdesk.processoms.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.edu.infnet.lawyerdesk.processoms.model.Endereco;
import br.edu.infnet.lawyerdesk.processoms.model.Processo;
import br.edu.infnet.lawyerdesk.processoms.service.ProcessoService;

@RestController
@RequestMapping("/processo")
public class ProcessoResource {

	@Autowired
	private ProcessoService processoService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public List<Processo> getAll() {
		return this.processoService.getAll();
	}

	@GetMapping("/advogado/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public List<ProcessoDTO> getByAdvogadoId(@PathVariable Long id) {
		return this.processoService.getByAdvogadoId(id);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public Optional<Processo> getById(@PathVariable Long id) {
		return this.processoService.getById(id);
	}

	@PostMapping("/vincularadv")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public void vincularAdvogado(@RequestBody VincularAdvogadoAProcessoDTO dto) throws Exception {
		this.processoService.vincularAdvogadoAProcesso(dto);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public ResponseEntity<Processo> save(@RequestBody ProcessoDTO dto) throws Exception {
		Processo saved = 	this.processoService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public void update(@RequestBody ProcessoDTO dto,@PathVariable Long id) throws Exception {
		this.processoService.update(id,dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		this.processoService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
