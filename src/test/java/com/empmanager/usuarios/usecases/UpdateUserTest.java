package com.empmanager.usuarios.usecases;

import com.empmanager.exception.UserNotFoundException;
import com.empmanager.dto.UsuarioDTO;
import com.empmanager.repository.UserRepository;
import com.empmanager.usecases.GetUser;
import com.empmanager.usecases.UpdateUser;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateUserTest {

    @InjectMocks
    private UpdateUser updateUser;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GetUser getUser;

    @Test
    void shouldUpdateUsuario(){
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
       var endereco = new Endereco( "Av Ernesto Igel", "307", "bloco 3", "SP", "SP");

       var user = new Usuario(1L, "Jackson Bispo", "Desenvolvedor Java pleno", new BigDecimal("14.000"), "991556628",endereco);
        when(getUser.execute(userId)).thenReturn(Optional.of(user));

        when(userRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Usuario result = updateUser.execute(usuarioDTO);

        Assertions.assertNotNull(result);

        verify(userRepository).save(result);
        Assertions.assertEquals("Jackson Bispo ", result.getName());
        Assertions.assertEquals("Desenvolvedor Java pleno", result.getDesignacao());
        Assertions.assertEquals(new BigDecimal("14.000"), result.getSalario());
        Assertions.assertEquals("991556628", result.getTelefone());
        Assertions.assertEquals("Av Ernesto Igel", result.getEndereco().getLogradouro());
        Assertions.assertEquals("307", result.getEndereco().getNumero());

    }

    @Test
    void shouldNotUpdateUsuario(){
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

        when(getUser.execute(userId)).thenThrow(UserNotFoundException.class);

        Assertions.assertThrows(UserNotFoundException.class, () -> updateUser.execute(usuarioDTO));

        verifyNoInteractions(userRepository);

    }


}
