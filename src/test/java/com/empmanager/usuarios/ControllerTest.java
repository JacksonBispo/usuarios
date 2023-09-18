package com.empmanager.usuarios;


import com.empmanager.controller.UserController;
import com.empmanager.exception.ControllerExceptionHandler;
import com.empmanager.repository.UserRepository;
import com.empmanager.usuarios.fixtures.EnderecoDTO;
import com.empmanager.usuarios.fixtures.SaveUsuarioDTO;
import com.empmanager.usuarios.fixtures.UpdateUsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class ControllerTest {

    private MockMvc mvc;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserController controller;

    @Autowired
    private JacksonTester<SaveUsuarioDTO> saveUsuarioDTOJacksonTester;
    @Autowired
    private JacksonTester<UpdateUsuarioDTO> updateUsuarioDTOJacksonTester;


    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void saveUser() throws Exception {
        var endereco = new EnderecoDTO("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var userDTO = new SaveUsuarioDTO("Jackson B", "Desenvolvedor JAVA", new BigDecimal("14.000"), "991556628", endereco);

        var response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/funcionarios/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveUsuarioDTOJacksonTester.write(userDTO).getJson())
        ).andReturn().getResponse();

    }


    @Test
    void testSaveUserFailed() throws Exception {
        var endereco = new EnderecoDTO("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var userDTO = new SaveUsuarioDTO("    ", "Desenvolvedor JAVA", new BigDecimal("14.000"), "991556628", endereco);

        var response =
                mvc.perform(MockMvcRequestBuilders.post("/api/funcionarios/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(saveUsuarioDTOJacksonTester.write(userDTO).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void updateUser() throws Exception {
        var endereco = new EnderecoDTO("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var userDTO = new UpdateUsuarioDTO("Jackson Bispo", "Desenvolvedor JAVA", new BigDecimal("14.000"), "991556628", endereco);

        var resultActions = mvc.perform(
                MockMvcRequestBuilders
                        .put("/api/funcionarios/atualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUsuarioDTOJacksonTester.write(new UpdateUsuarioDTO("Jackson Bispo", "Desenvolvedor JAVA", new BigDecimal("14.000"), "991556628", endereco)).getJson())
        ).andReturn().getResponse();

        var returnJson = updateUsuarioDTOJacksonTester.write(
                userDTO
        ).getJson();
    }

}
