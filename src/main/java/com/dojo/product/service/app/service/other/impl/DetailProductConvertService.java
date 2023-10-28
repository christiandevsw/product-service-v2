package com.dojo.product.service.app.service.other.impl;

import com.dojo.product.service.app.dto.DetailProductDTO;
import com.dojo.product.service.app.entity.Product;
import com.dojo.product.service.app.service.other.ConvertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DetailProductConvertService implements ConvertService<Product, DetailProductDTO> {
    private CategoryConvertService categoryConvert;


    @Override
    public Product convertToEntity(DetailProductDTO t) {
        if (t != null) {
            Product product = new Product();
            product.setUniqueIdentifier(t.getId());
            product.setName(t.getName());
            product.setPrice(t.getPrice());
            product.setDscto(t.getDscto());
            product.setCategory(categoryConvert.convertToEntity(t.getCategory()));
            product.setDescription(t.getDescription());
            product.setStock(t.getStock());
            product.setAvailable(t.getAvailable());
            product.setPhoto(t.getPhoto());
            return product;
        }
        return null;
    }

    @Override
    public DetailProductDTO convertToDto(Product t) {
        DetailProductDTO dto = new DetailProductDTO();
        dto.setId(t.getUniqueIdentifier());
        dto.setName(t.getName());
        dto.setPrice(t.getPrice());
        dto.setDescription(t.getDescription());
        dto.setCategory(categoryConvert.convertToDto(t.getCategory()));
        dto.setDscto(t.getDscto());
        dto.setStock(t.getStock());
        dto.setAvailable(t.getAvailable());
        dto.setPhoto(t.getPhoto());
        return dto;
    }
}
