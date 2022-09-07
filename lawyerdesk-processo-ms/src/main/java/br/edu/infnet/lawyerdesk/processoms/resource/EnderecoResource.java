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

import br.edu.infnet.lawyerdesk.processoms.dto.EnderecoDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Endereco;
import br.edu.infnet.lawyerdesk.processoms.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Endereco> getAll() {
		return this.enderecoService.getAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<Endereco> getById(@PathVariable Long id) {
		return this.enderecoService.getById(id);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public void save(@RequestBody EnderecoDTO dto) {
		this.enderecoService.save(dto);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public void update(@RequestBody EnderecoDTO dto) {
		this.enderecoService.save(dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteById(@PathVariable Long id) {
		this.enderecoService.delete(id);
	}

}
