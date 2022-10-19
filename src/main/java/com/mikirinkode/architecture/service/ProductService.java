package com.mikirinkode.architecture.service;

import com.mikirinkode.architecture.common.ResponseUtil;
import com.mikirinkode.architecture.form.ProductForm;
import com.mikirinkode.architecture.model.ProductModel;
import com.mikirinkode.architecture.repository.ProductRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

//    private productRepo: ProductRepository

    ProductModel create(ProductForm form);

    List<ProductModel> getAll();

    ResponseEntity<Object> findById(Long id);

    ResponseEntity<Object> updateById(ProductForm form, Long id);

    ResponseEntity<Object> deleteById(Long id);
}
