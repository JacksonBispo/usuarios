package com.empmanager.usuarios.usecases;

import com.empmanager.domain.Usuario;
import com.empmanager.dto.EnderecoDTO;
import com.empmanager.dto.SaveUsuarioDTO;
import com.empmanager.repository.UserRepository;
import com.empmanager.usecases.ListUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetListUserTest {

    @InjectMocks
    private ListUser listUser;

    @Mock
    private UserRepository userRepository;


    @Test
    void shouldListUserTest() {
        Long userId = 1L;
        var endereco = new EnderecoDTO("Av Ernesto Igel", "307", "bloco 3", "SP", "SP");
        var userDTO = new SaveUsuarioDTO("Jackson", "Desenvolvedor Java", new BigDecimal("14.000"), "991556628", endereco);
        var usuario = new Usuario(userDTO);

        var mockUser = new Usuario(userDTO);
        mockUser.setId(userId);
        when(userRepository.findAll()).thenReturn(List.of(mockUser));
        var result = listUser.execute();

        verify(userRepository).findAll();

    }
}
