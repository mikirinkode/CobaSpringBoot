package com.mikirinkode.architecture.controller;

import com.mikirinkode.architecture.form.ProductForm;
import com.mikirinkode.architecture.model.ProductModel;
import com.mikirinkode.architecture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ProductModel create(@RequestBody ProductForm form) {
        return productService.create(form);
    }

    @GetMapping
    public List<ProductModel> getAll() {
        return productService.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping(value = "{id}/update")
    public ResponseEntity<Object> updateById(@RequestBody ProductForm form, @PathVariable Long id) {
        return productService.updateById(form, id);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        return productService.deleteById(id);
    }
}
