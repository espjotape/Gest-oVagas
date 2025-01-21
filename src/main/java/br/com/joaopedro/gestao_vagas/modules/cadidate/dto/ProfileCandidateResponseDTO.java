package br.com.joaopedro.gestao_vagas.modules.cadidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    
    private String username; 
    private String email;
    private UUID id;
    private String name;
    private String description;
}
