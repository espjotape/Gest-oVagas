package br.com.joaopedro.gestao_vagas.modules.cadidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateEntity;
import br.com.joaopedro.gestao_vagas.modules.cadidate.useCase.CreateCandidateUseCase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create( @Valid @RequestBody CandidateEntity candidateEntity) {
        try {
           var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
