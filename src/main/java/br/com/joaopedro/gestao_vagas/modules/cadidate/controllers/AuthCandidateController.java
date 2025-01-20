package br.com.joaopedro.gestao_vagas.modules.cadidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.joaopedro.gestao_vagas.modules.cadidate.dto.AuthCandidateRequestDTO;
import br.com.joaopedro.gestao_vagas.modules.cadidate.useCase.AuthCandidateUseCase;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;
    
    @PostMapping("/auth") 
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        }catch(Exception e) {
            
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        }
    }
        // Implementar a autenticação do candidato
}
