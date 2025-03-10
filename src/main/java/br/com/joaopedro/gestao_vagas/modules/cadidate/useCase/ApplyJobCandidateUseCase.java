package br.com.joaopedro.gestao_vagas.modules.cadidate.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateRepository;
import br.com.joaopedro.gestao_vagas.modules.cadidate.entity.ApplyJobEntity;
import br.com.joaopedro.gestao_vagas.modules.cadidate.repository.ApplyJobRepository;
import br.com.joaopedro.gestao_vagas.modules.company.repositories.JobRepository;
import br.com.joaopedro.gestao_vagas.exceptions.UserNotFoundException;
import br.com.joaopedro.gestao_vagas.exceptions.JobNotFoundException;


@Service
public class ApplyJobCandidateUseCase {
 
 @Autowired
 private CandidateRepository candidateRepository;

 @Autowired
 private JobRepository jobRepository;

 @Autowired
 private ApplyJobRepository applyJobRepository;

 public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
  
  // Validar se o candidato existe
  this.candidateRepository.findById(idCandidate)
   .orElseThrow(() -> {
     throw new UserNotFoundException();
   });

  // Validar se a vaga existe
   this.jobRepository.findById(idJob)
    .orElseThrow(() -> {
      throw new JobNotFoundException();
    });

    // Candidatose inscrever na vaga
    var applyJob = ApplyJobEntity.builder()
    .candidateId(idCandidate)
    .jobId(idJob).build();

    applyJob = applyJobRepository.save(applyJob);
    return applyJob;
 }

}
