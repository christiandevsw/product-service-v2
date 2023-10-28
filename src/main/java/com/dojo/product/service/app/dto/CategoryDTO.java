package com.dojo.product.service.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String id;
    private String name;
    private String description;
    @JsonIgnore
    private byte[] photo;
}
