package com.dojo.product.service.app.service.other.impl;

import com.dojo.product.service.app.dto.BenefitDTO;
import com.dojo.product.service.app.entity.Benefit;
import com.dojo.product.service.app.service.other.ConvertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BenefitConvertService implements ConvertService<Benefit, BenefitDTO> {
    private DetailProductConvertService detailProductConvert;
    @Override
    public Benefit convertToEntity(BenefitDTO t) {
        if (t!=null){
            Benefit benefit=new Benefit();
            benefit.setUniqueIdentifier(t.getId());
            benefit.setDescription(t.getDescription());
            benefit.setProduct(detailProductConvert.convertToEntity(t.getProduct()));
            return benefit;
        }
        return null;
    }

    @Override
    public BenefitDTO convertToDto(Benefit t) {
        BenefitDTO benefitDTO=new BenefitDTO();
        benefitDTO.setId(t.getUniqueIdentifier());
        benefitDTO.setDescription(t.getDescription());
        benefitDTO.setProduct(detailProductConvert.convertToDto(t.getProduct()));
        return benefitDTO;
    }
}
