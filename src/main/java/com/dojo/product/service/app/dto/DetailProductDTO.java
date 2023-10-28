package com.dojo.product.service.app.dto;

import com.dojo.product.service.app.dto.util.CategoryDTOSerializer;
import com.dojo.product.service.app.dto.util.SetBenefitDTOSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailProductDTO {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    @JsonSerialize(using = CategoryDTOSerializer.class)
    private CategoryDTO category;
    private BigDecimal dscto;
    private Integer stock;
    private Boolean available;
    @JsonIgnore
    private byte[] photo;
    @JsonSerialize(using = SetBenefitDTOSerializer.class)
    private Set<BenefitDTO> benefits = new HashSet<>();

}
