package br.com.joaopedro.gestao_vagas.modules.cadidate.useCase;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import br.com.joaopedro.gestao_vagas.modules.cadidate.CandidateRepository;
import br.com.joaopedro.gestao_vagas.modules.cadidate.dto.AuthCandidateRequestDTO;
import br.com.joaopedro.gestao_vagas.modules.cadidate.dto.AuthCandidateResponseDTO;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CandidateRepository candidateRepository;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("Username/Password incorrect");
            });

        var passwordMatches = this.passwordEncoder
            .matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("Password does not match");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create()
            .withIssuer("javagas")
            .withSubject(candidate.getId().toString())
            .withClaim("roles", Arrays.asList("candidate"))
            .withExpiresAt(Instant.now().plus(Duration.ofMinutes(10)))
            .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
           .access_token(token)
           .expires_in(expiresIn.toEpochMilli())
           .build();

        return authCandidateResponse;  // Retorna o objeto AuthCandidateResponseDTO
    }
}
