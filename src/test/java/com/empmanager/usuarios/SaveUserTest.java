package com.empmanager.usuarios;

import com.empmanager.repository.UserRepository;
import com.empmanager.usecases.SaveUser;
import com.empmanager.usuarios.domain.Endereco;
import com.empmanager.usuarios.domain.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class SaveUserTest {

    @InjectMocks
    private SaveUser saveUser;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldSaveNewUser(){

        var endereco = new Endereco( "Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var usuario = new Usuario(null, "Jackson", "Desenvolvedor Java", new BigDecimal("14.000"), "991556628",endereco);
        Mockito.when(userRepository.save(any())).thenReturn(usuario);
        var userSaved = saveUser.execute(usuario);

        Assertions.assertEquals(usuario.getId(), userSaved.getId());
        Assertions.assertEquals(usuario.getName(), userSaved.getName());
        Assertions.assertEquals(usuario.getDesignacao(), userSaved.getDesignacao());
        Assertions.assertEquals(usuario.getSalario(), userSaved.getSalario());
        Assertions.assertEquals(usuario.getTelefone(), userSaved.getTelefone());
        Assertions.assertEquals(usuario.getEndereco().getLogradouro(), userSaved.getEndereco().getLogradouro());
        Assertions.assertEquals(usuario.getEndereco().getNumero(), userSaved.getEndereco().getNumero());
        Assertions.assertEquals(usuario.getEndereco().getComplemento(), userSaved.getEndereco().getComplemento());
        Assertions.assertEquals(usuario.getEndereco().getCidade(), userSaved.getEndereco().getCidade());
        Assertions.assertEquals(usuario.getEndereco().getEstado(), userSaved.getEndereco().getEstado());

    }
}
