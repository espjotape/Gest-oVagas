package br.com.joaopedro.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException {
 public JobNotFoundException() {
  super("Job not found");
 }
 
}
