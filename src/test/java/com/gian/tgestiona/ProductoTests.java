package com.gian.tgestiona;

import com.gian.tgestiona.model.ProductoEntity;
import com.gian.tgestiona.model.UsuarioEntity;
import com.gian.tgestiona.repository.ProductoRepository;
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
public class ProductoTests {
    private final List<ProductoEntity> lista = new ArrayList<>();
    private ProductoEntity firstProduct = new ProductoEntity();
    private ProductoEntity secondProduct = new ProductoEntity();


    @MockBean
    private ProductoRepository service;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setup(){
        firstProduct.setPrecio(BigDecimal.valueOf(100));
        firstProduct.setNombre("testPass");
        firstProduct.setCodProducto("testNombre");
        lista.add(firstProduct);

        secondProduct.setPrecio(BigDecimal.valueOf(200));
        secondProduct.setNombre("testPass");
        secondProduct.setCodProducto("testNombre");
        lista.add(secondProduct);
    }

    @Test
    public void userGetAllTest(){
        when(service.findAll()).thenReturn(lista);
        List<ProductoEntity> listaTest = service.findAll();
        assertNotNull(listaTest);
        assertEquals(2,listaTest.size());
    }

    @Test
    public void userGetByIdTest(){
        when(service.findById("testCodUsuario")).thenReturn(Optional.ofNullable(firstProduct));
        ProductoEntity foundProduct = service.findById("testCodUsuario").get();
        assertNotNull(foundProduct);
        assertEquals(firstProduct.getCodProducto(),foundProduct.getCodProducto());
    }

    @Test
    public void userUpdateSave(){
        when(service.save(firstProduct)).thenReturn(firstProduct);
        ProductoEntity saveProduct = service.save(firstProduct);
        assertNotNull(saveProduct);
        assertEquals(firstProduct.getCodProducto(),saveProduct.getCodProducto());
    }

    @Test
    public void deleteUser(){
        service.deleteById(firstProduct.getCodProducto());
        verify(service).deleteById(any());
    }

}
