package br.com.joaopedro.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaopedro.gestao_vagas.modules.company.repositories.JobRepository;
import br.com.joaopedro.gestao_vagas.modules.company.entities.JobEntity;

@Service
public class CreateJobUseCase {
    
    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
      return this.jobRepository.save(jobEntity);
    }
}
