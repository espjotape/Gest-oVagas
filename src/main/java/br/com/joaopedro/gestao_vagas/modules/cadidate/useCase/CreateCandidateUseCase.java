package br.com.joaopedro.gestao_vagas.modules.cadidate.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaopedro.gestao_vagas.exceptions.UserFoundException;
import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateEntity;
import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;
    
    public CandidateEntity execute(CandidateEntity candidateEntity) {
      this.candidateRepository
            .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundException();
            });

        return this.candidateRepository.save(candidateEntity);  
    }
}