package com.gian.tgestiona;

import com.gian.tgestiona.model.SucursalEntity;
import com.gian.tgestiona.model.SucursalEntity;
import com.gian.tgestiona.repository.ProductoRepository;
import com.gian.tgestiona.repository.SucursalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SucursalTests {

    private final List<SucursalEntity> lista = new ArrayList<>();
    private SucursalEntity firstSubsidiary = new SucursalEntity();
    private SucursalEntity secondSubsidiary = new SucursalEntity();


    @MockBean
    private SucursalRepository service;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setup(){
        firstSubsidiary.setCodSucursal("testCode");
        firstSubsidiary.setNombre("testPass");
        lista.add(firstSubsidiary);

        secondSubsidiary.setCodSucursal("codeTest");
        secondSubsidiary.setNombre("testPass");
        lista.add(secondSubsidiary);
    }

    @Test
    public void userGetAllTest(){
        when(service.findAll()).thenReturn(lista);
        List<SucursalEntity> listaTest = service.findAll();
        assertNotNull(listaTest);
        assertEquals(2,listaTest.size());
    }

    @Test
    public void userGetByIdTest(){
        when(service.findById("testCodUsuario")).thenReturn(Optional.ofNullable(firstSubsidiary));
        SucursalEntity foundSubsidiary = service.findById("testCodUsuario").get();
        assertNotNull(foundSubsidiary);
        assertEquals(firstSubsidiary.getCodSucursal(),foundSubsidiary.getCodSucursal());
    }

    @Test
    public void userUpdateSave(){
        when(service.save(firstSubsidiary)).thenReturn(firstSubsidiary);
        SucursalEntity saveSubsidiary = service.save(firstSubsidiary);
        assertNotNull(saveSubsidiary);
        assertEquals(firstSubsidiary.getCodSucursal(),saveSubsidiary.getCodSucursal());
    }

    @Test
    public void deleteUser(){
        service.deleteById(firstSubsidiary.getCodSucursal());
        verify(service).deleteById(any());
    }
    
}
