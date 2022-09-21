package br.edu.infnet.lawyerdesk.processoms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.infnet.lawyerdesk.processoms.model.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

	
	Optional<Processo> findByNumero(String numero);

	@Query("SELECT p FROM Processo p join p.advogados adv  WHERE adv.advogadoId = :advogadoId")
	List<Processo> findByAdv(Long advogadoId);

	
}

