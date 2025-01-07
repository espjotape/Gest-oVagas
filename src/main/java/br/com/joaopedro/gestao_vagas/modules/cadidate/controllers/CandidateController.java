package br.com.joaopedro.gestao_vagas.modules.cadidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public void create( @Valid @RequestBody CandidateEntity candidateEntity) {
        System.out.println("Candidato");
        System.out.println(candidateEntity.getEmail());
    }
}
