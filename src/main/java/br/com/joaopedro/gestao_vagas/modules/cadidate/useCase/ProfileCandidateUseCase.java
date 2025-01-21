package br.com.joaopedro.gestao_vagas.modules.cadidate.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateRepository;
import br.com.joaopedro.gestao_vagas.modules.cadidate.dto.ProfileCandidateResponseDTO;

public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
       var candidate = this.candidateRepository.findById(idCandidate)
       .orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
       });
       var candidateDTO = ProfileCandidateResponseDTO.builder()
       .description(candidate.getEmail())
       .username(candidate.getUsername())
       .email(candidate.getEmail())
       .name(candidate.getName())
       .id(candidate.getId())
       .build();

       return candidateDTO;
    }
}
