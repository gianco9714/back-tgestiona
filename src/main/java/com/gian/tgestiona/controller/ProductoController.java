package com.gian.tgestiona.controller;

import com.gian.tgestiona.exception.NotFoundException;
import com.gian.tgestiona.model.ProductoEntity;
import com.gian.tgestiona.repository.ProductoRepository;
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
@Api(value = "ProductoController", produces = "application/json", tags = { "Producto Controller" })
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/products")
    public List<ProductoEntity> getAllProducts(){
        return productoRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductoEntity> getProductByID(@PathVariable(value = "id") String productId) throws NotFoundException {
        ProductoEntity product = productoRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_PRODUCT_ID_MSG + productId));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public ProductoEntity createProduct(@RequestBody ProductoEntity productoEntity){
        return productoRepository.save(productoEntity);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductoEntity> updateProduct(@PathVariable (value = "id") String productId,
                                                        @RequestBody ProductoEntity productDetails) throws NotFoundException{
        ProductoEntity product = productoRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_PRODUCT_ID_MSG + productId));

        product.setNombre(productDetails.getNombre());
        product.setPrecio(productDetails.getPrecio());
        final ProductoEntity updateProduct = productoRepository.save(product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String productId) throws NotFoundException{
        ProductoEntity product = productoRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_PRODUCT_ID_MSG  + productId));

        productoRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Producto Borrado", Boolean.TRUE);
        return response;
    }

}
