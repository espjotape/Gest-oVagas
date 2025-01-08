package br.com.joaopedro.gestao_vagas.modules.cadidate;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    
}
