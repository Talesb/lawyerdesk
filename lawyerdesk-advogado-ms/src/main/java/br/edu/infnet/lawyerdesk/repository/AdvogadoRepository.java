package br.edu.infnet.lawyerdesk.repository;

import javax.enterprise.context.RequestScoped;

import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.lawyerdesk.model.Advogado;

@RequestScoped
public interface AdvogadoRepository extends CrudRepository<Advogado, Long> {

}
