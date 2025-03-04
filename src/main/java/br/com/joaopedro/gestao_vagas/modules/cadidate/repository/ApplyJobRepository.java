package br.com.joaopedro.gestao_vagas.modules.cadidate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaopedro.gestao_vagas.modules.cadidate.entity.ApplyJobEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

  
}