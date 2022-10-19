package com.mikirinkode.architecture.service;

import com.mikirinkode.architecture.common.ResponseUtil;
import com.mikirinkode.architecture.constant.MessageConstant;
import com.mikirinkode.architecture.form.ProductForm;
import com.mikirinkode.architecture.model.ProductModel;
import com.mikirinkode.architecture.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {

    private final ProductRepository productRepo;

    @Autowired
    public ProductServiceImplement(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ProductModel create(ProductForm form) {
        return productRepo.save(product(form));
    }

    @Override
    public List<ProductModel> getAll() {
        return productRepo.findAll();
    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        try {
            Optional<ProductModel> opt = productRepo.findById(id);
            return opt.map(productModel -> ResponseUtil.build(MessageConstant.SUCCESS, productModel, HttpStatus.OK)).orElseGet(() -> ResponseUtil.build(MessageConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateById(ProductForm form, Long id) {
        try {
            Optional<ProductModel> getById = productRepo.findById(id);
            if (!getById.isPresent()) return ResponseUtil.build(MessageConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            ProductModel product = getById.get();
            product.setProductName(form.getProductName());
            product.setPrice(form.getPrice());
            productRepo.save(product);
            return ResponseUtil.build(MessageConstant.UPDATE_SUCCESS , product, HttpStatus.OK);
        } catch (Exception e){
            return ResponseUtil.build(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deleteById(Long id) {
        try {
            Optional<ProductModel> getById = productRepo.findById(id);
            if (!getById.isPresent()) return ResponseUtil.build(MessageConstant.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            productRepo.deleteOne(true, getById.get().getId());
            return ResponseUtil.build(MessageConstant.DELETE_SUCCESS, null, HttpStatus.OK);
        } catch (Exception e){
            return ResponseUtil.build(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ProductModel product(ProductForm form) {
        ProductModel product = new ProductModel();
        product.setProductName(form.getProductName());
        product.setPrice(form.getPrice());
        return product;
    }

}
