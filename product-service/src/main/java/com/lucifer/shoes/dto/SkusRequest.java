package com.lucifer.shoes.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkusRequest {
    private Long skuId;

    private String color;

    private Integer size;

    private double price;

    private double discountedPrice;

    private Integer quantity;
}
