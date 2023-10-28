package com.dojo.product.service.app.dto;

import com.dojo.product.service.app.dto.util.DetailProductDTOSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class BenefitDTO {
    private String id;
    private String description;
    @JsonSerialize(using = DetailProductDTOSerializer.class)
    private DetailProductDTO product;
}
