package br.edu.infnet.lawyerdesk.repository;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;

import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.lawyerdesk.model.Advogado;

@RequestScoped
public interface AdvogadoRepository extends CrudRepository<Advogado, Long> {

	
	Optional<Advogado>findByOab(String oab);
}
