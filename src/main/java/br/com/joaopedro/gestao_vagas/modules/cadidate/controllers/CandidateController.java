package br.com.joaopedro.gestao_vagas.modules.cadidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping("/")
    public void create( @RequestBody CandidateEntity candidateEntity) {
        System.out.println("Candidato");
        System.out.println(candidateEntity.getEmail());
    }
}
