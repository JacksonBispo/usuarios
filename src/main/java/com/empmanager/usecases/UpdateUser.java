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
            endereco.setLogradouro(usuarioDTO.enderecoDTO().logradouro() != null ? usuarioDTO.enderecoDTO().logradouro() : user.get().getEndereco().getLogradouro());
            endereco.setComplemento(usuarioDTO.enderecoDTO().complemento() != null ? usuarioDTO.enderecoDTO().complemento() : user.get().getEndereco().getComplemento());
            endereco.setNumero(usuarioDTO.enderecoDTO().numero() != null ? usuarioDTO.enderecoDTO().numero() : user.get().getEndereco().getNumero());
            endereco.setCidade(usuarioDTO.enderecoDTO().cidade() != null ? usuarioDTO.enderecoDTO().cidade() : user.get().getEndereco().getCidade());
            endereco.setEstado(usuarioDTO.enderecoDTO().estado() != null ? usuarioDTO.enderecoDTO().estado() : user.get().getEndereco().getEstado());
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
