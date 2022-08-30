package br.edu.infnet.lawyerdesk.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.edu.infnet.lawyerdesk.model.Role;
import br.edu.infnet.lawyerdesk.repository.RoleRepository;

@RequestScoped
public class RoleService {

	@Inject
	private RoleRepository roleRepository;

	public List<Role> findAll() {
		return (List<Role>) this.roleRepository.findAll();
	}

	public void persist(Role especialidade) {
		this.roleRepository.save(especialidade);

	}

	public Role findById(Long id) {
		return this.roleRepository.findById(id).orElse(null);
	}

	public void deleteById(Long id) {
		this.roleRepository.deleteById(id);
	}

}
