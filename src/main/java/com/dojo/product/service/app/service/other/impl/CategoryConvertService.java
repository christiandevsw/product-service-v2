package com.dojo.product.service.app.service.other.impl;

import com.dojo.product.service.app.dto.CategoryDTO;
import com.dojo.product.service.app.entity.Category;
import com.dojo.product.service.app.service.other.ConvertService;
import org.springframework.stereotype.Component;

@Component
public class CategoryConvertService implements ConvertService<Category, CategoryDTO> {

    @Override
    public Category convertToEntity(CategoryDTO t) {
        if (t != null) {
            Category category = new Category();
            category.setName(t.getName());
            category.setUniqueIdentifier(t.getId());
            category.setDescription(t.getDescription());
            category.setPhoto(t.getPhoto());
            return category;
        }
        return null;
    }

    @Override
    public CategoryDTO convertToDto(Category t) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(t.getUniqueIdentifier());
        dto.setName(t.getName());
        dto.setDescription(t.getDescription());
        dto.setPhoto(t.getPhoto());
        return dto;
    }
}
