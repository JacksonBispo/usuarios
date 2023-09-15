package com.empmanager.usecases;

import com.empmanager.repository.UserRepository;
import com.empmanager.usuarios.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GetUser getUser;

    public void execute(Long id) {
        var user = getUser.execute(id).get();
        userRepository.delete(user);
    }

}

