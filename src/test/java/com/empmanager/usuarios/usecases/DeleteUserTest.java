package com.empmanager.usuarios.usecases;

import com.empmanager.domain.Usuario;
import com.empmanager.dto.EnderecoDTO;
import com.empmanager.dto.SaveUsuarioDTO;
import com.empmanager.repository.UserRepository;
import com.empmanager.usecases.DeleteUser;
import com.empmanager.usecases.GetUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteUserTest {

    @InjectMocks
    private DeleteUser deleteUser;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GetUser getUser;

    @Test
    void shouldDeleteUsuario() {
        Long userId = 1L;
        var endereco = new EnderecoDTO("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var userDTO = new SaveUsuarioDTO("Jackson", "Desenvolvedor JAVA", new BigDecimal("14.000"), "991556628", endereco);
        var usuario = new Usuario(userDTO);
        when(getUser.execute(userId)).thenReturn(Optional.of(usuario));

        deleteUser.execute(userId);

        verify(userRepository).delete(usuario);
    }
}

