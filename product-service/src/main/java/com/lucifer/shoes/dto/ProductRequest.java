package com.lucifer.shoes.dto;


import com.lucifer.shoes.model.Brand;
import com.lucifer.shoes.model.Category;
import com.lucifer.shoes.model.Skus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private Long id;

    private String title;

    private String description;

    private Long categoryId;

    private Long brandId;

    private List<SkusRequest> skus;

    private String images;

}
