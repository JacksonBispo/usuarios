package com.empmanager.usecases;

import com.empmanager.exception.UserNotFoundException;
import com.empmanager.repository.UserRepository;
import com.empmanager.usuarios.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUser {

    @Autowired
    private UserRepository userRepository;

    public Optional<Usuario> execute(Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()){
            return user;
        }else{
            throw new UserNotFoundException("Usuario nao encontrado!");
        }
    }
}
