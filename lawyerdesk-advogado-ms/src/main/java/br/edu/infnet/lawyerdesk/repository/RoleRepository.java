package br.edu.infnet.lawyerdesk.repository;

import javax.enterprise.context.RequestScoped;

import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.lawyerdesk.model.Role;

@RequestScoped
public interface RoleRepository extends CrudRepository<Role,Long>{

}
