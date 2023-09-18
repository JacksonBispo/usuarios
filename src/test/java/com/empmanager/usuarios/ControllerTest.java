package com.empmanager.usuarios;


import com.empmanager.controller.UserController;
import com.empmanager.domain.Endereco;
import com.empmanager.domain.Usuario;
import com.empmanager.exception.ControllerExceptionHandler;
import com.empmanager.repository.UserRepository;
import com.empmanager.usecases.DeleteUser;
import com.empmanager.usecases.ListUser;
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
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class ControllerTest {

    private MockMvc mvc;

    @Mock
    private UserRepository repository;

    @Mock
    private ListUser listUser;


    @Mock
    private DeleteUser deleteUser;
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
                mvc.perform(MockMvcRequestBuilders.get("/api/funcionarios/cadastrar")
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

    @Test
    void testUpdatUserFailed() throws Exception {
        var endereco = new EnderecoDTO("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var userDTO = new SaveUsuarioDTO("    ", "Desenvolvedor JAVA", new BigDecimal("14.000"), "991556628", endereco);

        var response =
                mvc.perform(MockMvcRequestBuilders.get("/api/funcionarios/atualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveUsuarioDTOJacksonTester.write(userDTO).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    void getUser() throws Exception {
        var endereco = new Endereco("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");

        List<Usuario> userList = Arrays.asList(
                new Usuario("Jackson Bispo", "Desenvolvedor JAVA", new BigDecimal("14000"), "991556628", endereco),
                new Usuario("Jackson Novaes", "Desenvolvedor JAVA PLENO", new BigDecimal("18.000"), "991556628", endereco)
        );

        when(listUser.execute()).thenReturn(userList);

        mvc.perform(MockMvcRequestBuilders.get("/api/funcionarios/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(userList.size()))
                .andExpect(jsonPath("$[0].name").value("Jackson Bispo"))
                .andExpect(jsonPath("$[0].designacao").value("Desenvolvedor JAVA"))
                .andExpect(jsonPath("$[0].salario").value("14000"))
                .andExpect(jsonPath("$[0].telefone").value("991556628"));

        // Verify that the mock service was called
        verify(listUser).execute();

    }

    @Test
    void testDeleteUser() throws Exception {
        Long userId = 1L;

        mvc.perform(delete("/api/funcionarios/" + userId))
                .andExpect(status().isOk()); // Expect a 200 OK response
        verify(deleteUser).execute(userId);
    }

}
