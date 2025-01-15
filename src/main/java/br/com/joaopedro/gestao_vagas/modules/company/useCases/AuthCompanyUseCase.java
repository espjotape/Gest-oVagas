package br.com.joaopedro.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.joaopedro.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.joaopedro.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> new UsernameNotFoundException("Username/Password incorrect")
        );

        // Verificar se a senha fornecida corresponde à senha armazenada
        boolean passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        
        // Se as senhas não coincidirem, lançar uma exceção
        if (!passwordMatches) {
            throw new BadCredentialsException("Username/Password incorrect");
        }

        // Se as credenciais forem válidas, gerar o token JWT
        Algorithm algorithm = Algorithm.HMAC256(secretKey);  // Usar o valor correto da chave secreta
        var token = JWT.create()
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .withIssuer("javagas")
            .withSubject(company.getId().toString())
            .sign(algorithm);
        return token;
    }
}
