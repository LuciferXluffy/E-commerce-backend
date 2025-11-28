package com.lucifer.shoes.controller;

import com.lucifer.shoes.dto.ProductRequest;
import com.lucifer.shoes.model.Product;
import com.lucifer.shoes.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    //get all the products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProduct() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
        return "PRODUCT CREATED";
    }
}
