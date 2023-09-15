package com.empmanager.usecases;

import com.empmanager.dto.UsuarioDTO;
import com.empmanager.repository.UserRepository;
import com.empmanager.usuarios.domain.Endereco;
import com.empmanager.usuarios.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GetUser getUser;

    public Usuario execute(UsuarioDTO usuarioDTO) {
        var user = getUser.execute(usuarioDTO.id());

            var endereco = new Endereco();
            endereco.setLogradouro(usuarioDTO.logradouro() != null ? usuarioDTO.logradouro() : user.get().getEndereco().getLogradouro());
            endereco.setComplemento(usuarioDTO.complemento() != null ? usuarioDTO.complemento() : user.get().getEndereco().getComplemento());
            endereco.setNumero(usuarioDTO.numero() != null ? usuarioDTO.numero() : user.get().getEndereco().getNumero());
            endereco.setCidade(usuarioDTO.cidade() != null ? usuarioDTO.cidade() : user.get().getEndereco().getCidade());
            endereco.setEstado(usuarioDTO.estado() != null ? usuarioDTO.estado() : user.get().getEndereco().getEstado());
            var usuario = new Usuario();
            usuario.setId(usuarioDTO.id());
            usuario.setName(usuarioDTO.name() != null ? usuarioDTO.name() : user.get().getName());
            usuario.setDesignacao(usuarioDTO.designacao() != null ? usuarioDTO.designacao() : user.get().getDesignacao());
            usuario.setSalario(usuarioDTO.salario() != null ? usuarioDTO.salario() : user.get().getSalario());
            usuario.setTelefone(usuarioDTO.telefone() != null ? usuarioDTO.telefone() : user.get().getTelefone());
            usuario.setEndereco(endereco);

            return userRepository.save(usuario);

    }
}
