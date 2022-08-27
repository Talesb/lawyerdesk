package br.edu.infnet.lawyerdesk.repository;


import javax.enterprise.context.RequestScoped;

import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.lawyerdesk.model.Administrador;

@RequestScoped
public interface AdministradorRepository extends CrudRepository<Administrador, Long>{

}
