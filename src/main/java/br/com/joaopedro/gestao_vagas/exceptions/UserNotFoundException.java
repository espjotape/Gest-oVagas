package br.com.joaopedro.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {
 public UserNotFoundException() {
  super("User not found");
 }
 
}
