package com.empmanager.usecases;

import com.empmanager.dto.SaveUsuarioDTO;
import com.empmanager.repository.UserRepository;
import com.empmanager.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUser {


    @Autowired
    private UserRepository repository;


    public Usuario execute(SaveUsuarioDTO usuario){
        var user = new Usuario(usuario);
        return repository.save(user);
    }
}
