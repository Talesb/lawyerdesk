package br.edu.infnet.lawyerdesk.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.edu.infnet.lawyerdesk.model.Administrador;
import br.edu.infnet.lawyerdesk.model.Advogado;
import br.edu.infnet.lawyerdesk.repository.AdministradorRepository;

@RequestScoped
public class AdministradorService {

	@Inject
	private AdministradorRepository administradorRepository;

	public List<Administrador> findAll() {
		return (List<Administrador>) administradorRepository.findAll();
	}

	public void persist(Administrador administrador) {
		this.administradorRepository.save(administrador);
	}

	public Administrador findById(Long id) {
		return this.administradorRepository.findById(id).orElse(null);
	}

	public Administrador update(Long id, Administrador administradorParam) {
		Administrador adm = this.administradorRepository.findById(id).orElse(null);
		adm.setNome(administradorParam.getNome());
		adm.setCpf(administradorParam.getCpf());
		this.administradorRepository.save(adm);
		return adm;
	}

	public void deleteById(Long id) {
		this.administradorRepository.deleteById(id);
	}

}
