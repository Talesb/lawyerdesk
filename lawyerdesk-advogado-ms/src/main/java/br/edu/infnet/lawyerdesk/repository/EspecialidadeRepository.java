package br.edu.infnet.lawyerdesk.repository;

import javax.enterprise.context.RequestScoped;

import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.lawyerdesk.model.Especialidade;

@RequestScoped
public interface EspecialidadeRepository extends CrudRepository<Especialidade, Long> {

}
