package br.com.joaopedro.gestao_vagas.modules.cadidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateEntity;
import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create( @Valid @RequestBody CandidateEntity candidateEntity) {
       return this.candidateRepository.save(candidateEntity);
    }
}
