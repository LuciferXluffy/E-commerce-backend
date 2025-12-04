package com.lucifer.shoes.service;

import com.lucifer.shoes.dto.ProductRequest;
import com.lucifer.shoes.model.Brand;
import com.lucifer.shoes.model.Category;
import com.lucifer.shoes.model.Product;
import com.lucifer.shoes.model.Skus;
import com.lucifer.shoes.repository.BrandRepository;
import com.lucifer.shoes.repository.CategoryRepository;
import com.lucifer.shoes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(ProductRequest productRequest) {

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Brand brand = brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Product product = new Product();
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setCategory(category);
        product.setBrand(brand);
        product.setImages(productRequest.getImages());

        List<Skus> skus = productRequest.getSkus().stream()
                .map(skuReq -> {
                    Skus sku = new Skus();
                    sku.setColor(skuReq.getColor());
                    sku.setSize(skuReq.getSize());
                    sku.setPrice(skuReq.getPrice());
                    sku.setDiscountedPrice(skuReq.getDiscountedPrice());
                    sku.setQuantity(skuReq.getQuantity());
                    sku.setProduct(product);
                    return sku;
                }).toList();

        product.setSkus(skus);

        productRepository.save(product);
    }


}
