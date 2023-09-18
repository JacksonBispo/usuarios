package com.empmanager.usecases;

import com.empmanager.domain.Usuario;
import com.empmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUser {

    @Autowired
    private UserRepository repository;

    public List<Usuario> execute() {
        return repository.findAll();
    }
}
