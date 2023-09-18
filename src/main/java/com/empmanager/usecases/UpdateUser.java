package com.empmanager.usecases;

import com.empmanager.domain.Endereco;
import com.empmanager.domain.Usuario;
import com.empmanager.dto.UpdateUsuarioDTO;
import com.empmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GetUser getUser;

    public Usuario execute(Long id, UpdateUsuarioDTO usuarioDTO) {
        var user = getUser.execute(id);

            var endereco = new Endereco(usuarioDTO.endereco());
            endereco.setLogradouro(usuarioDTO.endereco().logradouro() != null ? usuarioDTO.endereco().logradouro() : user.get().getEndereco().getLogradouro());
            endereco.setComplemento(usuarioDTO.endereco().complemento() != null ? usuarioDTO.endereco().complemento() : user.get().getEndereco().getComplemento());
            endereco.setNumero(usuarioDTO.endereco().numero() != null ? usuarioDTO.endereco().numero() : user.get().getEndereco().getNumero());
            endereco.setCidade(usuarioDTO.endereco().cidade() != null ? usuarioDTO.endereco().cidade() : user.get().getEndereco().getCidade());
            endereco.setEstado(usuarioDTO.endereco().estado() != null ? usuarioDTO.endereco().estado() : user.get().getEndereco().getEstado());
            var usuario = new Usuario();
            usuario.setId(id);
            usuario.setName(usuarioDTO.name() != null ? usuarioDTO.name() : user.get().getName());
            usuario.setDesignacao(usuarioDTO.designacao() != null ? usuarioDTO.designacao() : user.get().getDesignacao());
            usuario.setSalario(usuarioDTO.salario() != null ? usuarioDTO.salario() : user.get().getSalario());
            usuario.setTelefone(usuarioDTO.telefone() != null ? usuarioDTO.telefone() : user.get().getTelefone());
            usuario.setEndereco(endereco);

            return userRepository.save(usuario);

    }
}
