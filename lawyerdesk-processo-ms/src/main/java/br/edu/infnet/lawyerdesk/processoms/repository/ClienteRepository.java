package br.edu.infnet.lawyerdesk.processoms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.infnet.lawyerdesk.processoms.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
	@Query("SELECT distinct c FROM Cliente c join c.processos processo join processo.advogados adv  WHERE adv.advogadoId = :advogadoId")
	List<Cliente> findByAdv(Long advogadoId);
	
}
