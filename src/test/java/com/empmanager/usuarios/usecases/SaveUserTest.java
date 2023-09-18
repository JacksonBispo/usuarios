package com.empmanager.usuarios.usecases;

import com.empmanager.domain.Usuario;
import com.empmanager.dto.EnderecoDTO;
import com.empmanager.dto.SaveUsuarioDTO;
import com.empmanager.repository.UserRepository;
import com.empmanager.usecases.SaveUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        var endereco = new EnderecoDTO("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var userDTO = new SaveUsuarioDTO("Jackson B", "Desenvolvedor JAVA",  new BigDecimal("14.000"), "991556628",endereco);
        var usuario = new Usuario(userDTO);
        Mockito.when(userRepository.save(any())).thenReturn(usuario);
        var userSaved = saveUser.execute(userDTO);

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
