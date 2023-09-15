package com.empmanager.usuarios.usecases;

import com.empmanager.exception.UserNotFoundException;
import com.empmanager.dto.UsuarioDTO;
import com.empmanager.repository.UserRepository;
import com.empmanager.usecases.GetUser;
import com.empmanager.usuarios.domain.Endereco;
import com.empmanager.usuarios.domain.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetUserTest {

    @InjectMocks
    private GetUser getUser;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldOneUserTest() {
        Long userId = 1L;
        var usuarioDTO = new UsuarioDTO(
                userId, "Jackson Bispo ",
                "Desenvolvedor Java pleno",
                new BigDecimal("14.000"),
                "991556628",
                "Av Ernesto Igel",
                "307",
                "bloco 3",
                "SP", "SP");
        var endereco = new Endereco("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");

        var mockUser = new Usuario(1L, "Jackson Bispo", "Desenvolvedor Java pleno", new BigDecimal("14.000"), "991556628", endereco);
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
