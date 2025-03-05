package br.com.joaopedro.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.joaopedro.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.joaopedro.gestao_vagas.modules.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {
 // Usado para simular requisições HTTP na API.
 private MockMvc mvc;

 // Fornece acesso ao ambiente do Spring, onde a API roda.
 @Autowired
 private WebApplicationContext context;

 @Before // Executa este método antes de cada teste.
 public void setup(){
  //  Configura o MockMvc para rodar com o contexto da aplicação.
  mvc = MockMvcBuilders
  .webAppContextSetup(context)
  .apply(SecurityMockMvcConfigurers.springSecurity())
  .build();
 }

 @Test
 public void should_be_able_to_create_a_new_job() throws Exception{
  var createdJobDTO = CreateJobDTO.builder()
   .benefits("BENEFITS_TEST")
   .description("DESCRIPTION_TEST")
   .level("LEVEL_TEST")
   .build();

   var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
    .contentType(MediaType.APPLICATION_JSON)
    .content(TestUtils.objectToJson(createdJobDTO))
    .header("Authorization", TestUtils.generateToken(UUID.fromString("56305a03-b7f5-40c5-8334-e50727242f04"), "JAVAGAS_@123#"))
    )
    // Converte o createdJobDTO para JSON e envia como corpo da requisição.
   .andExpect(MockMvcResultMatchers.status().isOk()); // Espera que a resposta seja 200 OK, ou seja, a vaga foi criada com sucesso.
   
   System.out.println(result);
 }


 }

