package com.empmanager.usecases;

import com.empmanager.repository.UserRepository;
import com.empmanager.usuarios.domain.Usuario;
import org.springframework.stereotype.Service;

@Service
public class SaveUser {

    private final UserRepository repository;

    public SaveUser(UserRepository repository) {
        this.repository = repository;
    }

    public Usuario execute(Usuario usuario){
        return repository.save(usuario);
    }
}
