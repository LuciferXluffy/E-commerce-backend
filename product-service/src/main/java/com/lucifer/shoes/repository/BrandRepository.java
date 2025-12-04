package com.lucifer.shoes.repository;

import com.lucifer.shoes.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
