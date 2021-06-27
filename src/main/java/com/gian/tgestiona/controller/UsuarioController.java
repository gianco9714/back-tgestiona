package com.gian.tgestiona.controller;

import com.gian.tgestiona.exception.NotFoundException;
import com.gian.tgestiona.model.UsuarioEntity;
import com.gian.tgestiona.repository.UsuarioRepository;
import com.gian.tgestiona.util.Constants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Api(value = "UsuarioController", produces = "application/json", tags = { "Usuario Controller" })
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/users")
    public List<UsuarioEntity> getAllUsers(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UsuarioEntity> getUsertByID(@PathVariable(value = "id") String userId) throws NotFoundException {
        UsuarioEntity user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_USER_ID_MSG + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public UsuarioEntity createUser(@RequestBody UsuarioEntity usuarioEntity){
        return usuarioRepository.save(usuarioEntity);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsuarioEntity> updateUser(@PathVariable (value = "id") String userId,
                                                        @RequestBody UsuarioEntity userDetails) throws NotFoundException{
        UsuarioEntity user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_USER_ID_MSG + userId));

        user.setUser(userDetails.getUser());
        user.setPassword(userDetails.getPassword());
        user.setNombre(userDetails.getNombre());
        user.setCodSucursal(userDetails.getCodSucursal());
        final UsuarioEntity updateUser = usuarioRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") String userId) throws NotFoundException{
        UsuarioEntity user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_USER_ID_MSG + userId));

        usuarioRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Usuario Borrado", Boolean.TRUE);
        return response;
    }
}
