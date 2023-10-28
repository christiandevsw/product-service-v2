package com.dojo.product.service.app.service.other.impl;

import com.dojo.product.service.app.dto.BasicProductDTO;
import com.dojo.product.service.app.entity.Product;
import com.dojo.product.service.app.service.other.ConvertService;
import org.springframework.stereotype.Component;

@Component
public class BasicProductConvertService implements ConvertService<Product, BasicProductDTO> {
    @Override
    public Product convertToEntity(BasicProductDTO t) {
        if (t!=null){
            Product product=new Product();
            product.setUniqueIdentifier(t.getId());
            product.setName(t.getName());
            product.setDescription(t.getDescription());
            product.setPrice(t.getPrice());
            product.setDscto(t.getDscto());

            return product;

        }
        return null;
    }

    @Override
    public BasicProductDTO convertToDto(Product t) {
        BasicProductDTO basicProductDTO=new BasicProductDTO();
        basicProductDTO.setId(t.getUniqueIdentifier());
        basicProductDTO.setName(t.getName());
        basicProductDTO.setPrice(t.getPrice());
        basicProductDTO.setDscto(t.getDscto());
        basicProductDTO.setDescription(t.getDescription());
        return basicProductDTO;
    }
}
