package com.lucifer.shoes.service;

import com.lucifer.shoes.model.Product;
import com.lucifer.shoes.model.Sizes;
import com.lucifer.shoes.model.Variant;
import com.lucifer.shoes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct(Product product) {

        Product newProduct = new Product();
        newProduct.setBrand(product.getBrand());
        newProduct.setDescription(product.getDescription());
        newProduct.setFrontImg(product.getFrontImg());
        newProduct.setPrice(product.getPrice());
        newProduct.setTitle(product.getTitle());

        // Map variants properly
        List<Variant> mappedVariants = product.getListOfVariant()
                .stream()
                .map(variant -> {

                    Variant newVariant = new Variant();
                    newVariant.setColor(variant.getColor());
                    newVariant.setImgUrl(variant.getImgUrl());
                    newVariant.setProduct(newProduct);   // correct parent

                    // map sizes
                    List<Sizes> mappedSizes = variant.getListOfSizes()
                            .stream()
                            .map(size -> {
                                Sizes newSize = new Sizes();
                                newSize.setSize(size.getSize());
                                newSize.setSku(size.getSku());
                                newSize.setStockQuantity(size.getStockQuantity());
                                newSize.setVariant(newVariant);
                                return newSize;
                            })
                            .toList();

                    newVariant.setListOfSizes(mappedSizes);
                    return newVariant;
                })
                .toList();

        newProduct.setListOfVariant(mappedVariants);

        productRepository.save(newProduct);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
