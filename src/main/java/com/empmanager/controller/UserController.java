package com.empmanager.controller;

import com.empmanager.dto.InfoUsuarioDTO;
import com.empmanager.dto.SaveUsuarioDTO;
import com.empmanager.dto.UpdateUsuarioDTO;
import com.empmanager.usecases.GetUser;
import com.empmanager.usecases.ListUser;
import com.empmanager.usecases.SaveUser;
import com.empmanager.usecases.UpdateUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/funcionarios")
public class UserController {

    @Autowired
    private SaveUser saveUser;
    @Autowired
    private UpdateUser updateUser;
    @Autowired
    private GetUser getUser;
    @Autowired
    private ListUser listUser;



    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid SaveUsuarioDTO user, UriComponentsBuilder uriBuilder){
        var usuario = saveUser.execute(user);
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid UpdateUsuarioDTO user, UriComponentsBuilder uriBuilder){
        var usuario = updateUser.execute(id,user);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoUsuarioDTO> getFuncionarios(@PathVariable Long id){

        var usuario = getUser.execute(id);

        var userDTO = new InfoUsuarioDTO(
                usuario.get().getName(),
                usuario.get().getDesignacao(),
                usuario.get().getSalario(),
                usuario.get().getTelefone(),
                usuario.get().getEndereco()
        );
        return ResponseEntity.ok(userDTO);

    }

    @GetMapping("/all")
    public List<InfoUsuarioDTO> getFuncionarios(){

        var users = listUser.execute()
        .stream()
                .map(InfoUsuarioDTO::new)
                .collect(Collectors.toList());

        return users;
    }
}
