package br.com.joaopedro.gestao_vagas.modules.cadidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    
    @Schema(example = "johndoe123")
    private String username;

    @Schema(example = "johndoe@example.com")
    private String email;
    private UUID id;

    @Schema(example = "John Doe")
    private String name;

    @Schema(example = "Desenvolvedor de software com 5 anos de experiência em backend")
    private String description;
}
