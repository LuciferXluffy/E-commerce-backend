package com.lucifer.shoes.dto;


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
