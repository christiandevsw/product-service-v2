package com.dojo.product.service.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicProductDTO {
    private String id;
    private String name;
    private BigDecimal price;
    private BigDecimal dscto;
    private String description;

}
