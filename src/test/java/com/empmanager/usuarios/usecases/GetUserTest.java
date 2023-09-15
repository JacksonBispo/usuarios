package com.empmanager.usuarios.usecases;

import com.empmanager.dto.EnderecoDTO;
import com.empmanager.dto.UsuarioDTO;
import com.empmanager.exception.UserNotFoundException;
import com.empmanager.repository.UserRepository;
import com.empmanager.usecases.GetUser;
import com.empmanager.usuarios.domain.Usuario;
import org.junit.jupiter.api.Assertions;
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
public class GetUserTest {

    @InjectMocks
    private GetUser getUser;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldOneUserTest() {
        Long userId = 1L;
        var endereco = new EnderecoDTO("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var userDTO = new UsuarioDTO(null, "Jackson", "Desenvolvedor Java", new BigDecimal("14.000"), "991556628",endereco);
        var usuario = new Usuario(userDTO);

        var mockUser = new Usuario(userDTO);
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        Usuario result = getUser.execute(userId).get();

        verify(userRepository).findById(mockUser.getId());

    }

    @Test
    void shouldErrorNotFoundUserTest() {
        Long userId = 1L;
        Assertions.assertThrows(UserNotFoundException.class, () -> getUser.execute(userId));
        verify(userRepository).findById(userId);
    }
}
