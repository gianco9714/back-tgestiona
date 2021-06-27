package com.gian.tgestiona;

import com.gian.tgestiona.model.UsuarioEntity;
import com.gian.tgestiona.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioTests {

    private final List<UsuarioEntity> lista = new ArrayList<>();
    private UsuarioEntity firstUser = new UsuarioEntity();
    private UsuarioEntity secondUser = new UsuarioEntity();


    @MockBean
    private UsuarioRepository service;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setup(){
        firstUser.setCodSucursal("testCode");
        firstUser.setPassword("testPass");
        firstUser.setNombre("testNombre");
        firstUser.setCodUsuario("testCodUsuario");
        firstUser.setUser("testUser");
        lista.add(firstUser);

        secondUser.setCodSucursal("testString");
        secondUser.setPassword("testString");
        secondUser.setNombre("testString");
        secondUser.setCodUsuario("testString");
        secondUser.setUser("testString");
        lista.add(secondUser);
    }

    @Test
    public void userGetAllTest(){
        when(service.findAll()).thenReturn(lista);
        List<UsuarioEntity> listaTest = service.findAll();
        assertNotNull(listaTest);
        assertEquals(2,listaTest.size());
    }

    @Test
    public void userGetByIdTest(){
        when(service.findById("testCodUsuario")).thenReturn(Optional.ofNullable(firstUser));
        UsuarioEntity foundUser = service.findById("testCodUsuario").get();
        assertNotNull(foundUser);
        assertEquals(firstUser.getCodUsuario(),foundUser.getCodUsuario());
    }

    @Test
    public void UpdateSave(){
        when(service.save(firstUser)).thenReturn(firstUser);
        UsuarioEntity saveUser = service.save(firstUser);
        assertNotNull(saveUser);
        assertEquals(firstUser.getCodUsuario(),saveUser.getCodUsuario());
    }

    @Test
    public void deleteUser(){
        service.deleteById(firstUser.getCodUsuario());
        verify(service).deleteById(any());
    }

}
