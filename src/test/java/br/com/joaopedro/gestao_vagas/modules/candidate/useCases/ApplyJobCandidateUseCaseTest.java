package br.com.joaopedro.gestao_vagas.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.joaopedro.gestao_vagas.exceptions.JobNotFoundException;
import br.com.joaopedro.gestao_vagas.exceptions.UserNotFoundException;
import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateEntity;
import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateRepository;
import br.com.joaopedro.gestao_vagas.modules.cadidate.useCase.ApplyJobCandidateUseCase;
import br.com.joaopedro.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

 @InjectMocks
 private ApplyJobCandidateUseCase applyJobCandidateUseCase;
 
 @Mock
 private CandidateRepository candidateRepository;
 
 @Mock
 private JobRepository jobRepository;

 @Test
 @DisplayName("Should not should not be able to create apply job with candidate not found")
 public void should_not_be_able_to_create_apply_job_with_candidate_not_found() {
  try {
    applyJobCandidateUseCase.execute(null, null);
  } catch (Exception e) {
    // Assegurando que tipo vai ser do mesmo tipo de UserNotFoundException
    assertThat(e).isInstanceOf(UserNotFoundException.class);
  }
 }

 @Test
 public void should_not_be_able_to_apply_job_with_job_not_found() {
  var idCandidate = UUID.randomUUID();

  var candidate = new CandidateEntity();
  candidate.setId(idCandidate);

  when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate)); // Simula que o candidato existe

  try {
    applyJobCandidateUseCase.execute(idCandidate, null);
  } catch (Exception e) {
    assertThat(e).isInstanceOf(JobNotFoundException.class);
  }
 }
}