package br.edu.infnet.lawyerdesk.auth.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import br.edu.infnet.lawyerdesk.auth.model.Role;

@RequestScoped
public class RoleService {

	public List<Role> findAll() {
		return Role.listAll();
	}

	public void persist(Role role) {
		Role.persist(role);

	}

	public Role findById(Long id) {
		return Role.findById(id);
	}

	public void deleteById(Long id) {
		Role.deleteById(id);
	}

}
