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

import br.edu.infnet.lawyerdesk.processoms.dto.SistemaDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Sistema;
import br.edu.infnet.lawyerdesk.processoms.service.SistemaService;

@RestController
@RequestMapping("/sistema")
public class SistemaResource {

	@Autowired
	private SistemaService sistemaService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public List<Sistema> getAll() {
		return this.sistemaService.getAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public Optional<Sistema> getById(@PathVariable Long id) {
		return this.sistemaService.getById(id);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public void save(@RequestBody SistemaDTO dto) {
		this.sistemaService.save(dto);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public void update(@RequestBody SistemaDTO dto) {
		this.sistemaService.save(dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public void deleteById(@PathVariable Long id) {
		this.sistemaService.delete(id);
	}

}
