package br.com.joaopedro.gestao_vagas.modules.cadidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateEntity;
import br.com.joaopedro.gestao_vagas.modules.cadidate.useCase.CreateCandidateUseCase;
import br.com.joaopedro.gestao_vagas.modules.cadidate.useCase.ProfileCandidateUseCase;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

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

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {
       
        var idCandidate = request.getAttribute("candidate_id");
        try {
         var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
         return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
}
