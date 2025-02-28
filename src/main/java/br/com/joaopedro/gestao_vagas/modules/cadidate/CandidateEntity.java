package br.com.joaopedro.gestao_vagas.modules.cadidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "cadidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Schema(example = "João Pedro", description = "Nome do candidato")
    private String name;

    @NotBlank
    @Pattern (regexp = "\\S+", message = "O campo [username] não deve conter espaços")
    @Schema(example = "jotape", description = "Nome de usuário do candidato (não deve conter espaços)")
    private String username;
    
    @Email( message = "O campo [email] deve conter um e-mail válido.")
    @Schema(example = "joaopedro@example.com", description = "E-mail válido do candidato")
    private String email;
    
    @Length( min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    @Schema(example = "senhaSuperSecreta123", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;
    @Schema(example = "Candidato com experiência em desenvolvimento backend")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}