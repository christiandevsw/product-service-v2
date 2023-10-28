package com.dojo.product.service.app.service;

import com.dojo.product.service.app.dto.BenefitDTO;

import java.util.Map;

public interface BenefitService {
    BenefitDTO save(BenefitDTO benefitDTO);

    BenefitDTO update(String id,BenefitDTO benefitDTO);

    BenefitDTO delete(String id, Map<String,String> headers);
}
