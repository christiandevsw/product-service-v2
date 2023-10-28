package com.dojo.product.service.app.service.impl;

import com.dojo.product.service.app.dto.BenefitDTO;
import com.dojo.product.service.app.dto.DetailProductDTO;
import com.dojo.product.service.app.entity.Benefit;
import com.dojo.product.service.app.entity.Category;
import com.dojo.product.service.app.entity.Product;
import com.dojo.product.service.app.repository.BenefitRepository;
import com.dojo.product.service.app.repository.CategoryRepository;
import com.dojo.product.service.app.repository.ProductRepository;
import com.dojo.product.service.app.service.BenefitService;
import com.dojo.product.service.app.service.other.impl.BenefitConvertService;
import com.dojo.product.service.app.util.ConstantsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BenefitServiceImpl implements BenefitService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private BenefitRepository benefitRepository;
    private BenefitConvertService benefitConvert;

    @Override
    @Transactional
    public BenefitDTO save(BenefitDTO benefitDTO) {
        DetailProductDTO detailProductDTO =benefitDTO.getProduct();
        Optional<Category> optionalCategory =categoryRepository.findByUniqueIdentifier(detailProductDTO.getCategory().getId());
        if (optionalCategory.isEmpty()) return null;

        Optional<Product> optionalProduct=productRepository.findByUniqueIdentifier(detailProductDTO.getId());
        if (optionalProduct.isEmpty()) return null;

        benefitDTO.setId(UUID.randomUUID().toString());
        Benefit newBenefit =benefitConvert.convertToEntity(benefitDTO);
        newBenefit.setProduct(optionalProduct.get());
        return benefitConvert.convertToDto(benefitRepository.save(newBenefit));
    }

    @Override
    @Transactional
    public BenefitDTO update(String id, BenefitDTO benefitDTO) {
        DetailProductDTO detailProductDTO =benefitDTO.getProduct();
        Optional<Category> optionalCategory =categoryRepository.findByUniqueIdentifier(detailProductDTO.getCategory().getId());
        if (optionalCategory.isEmpty()) return null;

        Optional<Product> optionalProduct=productRepository.findByUniqueIdentifier(detailProductDTO.getId());
        if (optionalProduct.isEmpty()) return null;

        Optional<Benefit> optionalBenefit=benefitRepository.findByUniqueIdentifier(id);

        if (optionalBenefit.isEmpty()) return null;
        Benefit currentBenefit=optionalBenefit.get();
        currentBenefit.setDescription(benefitDTO.getDescription());
        currentBenefit.setProduct(optionalProduct.get());
        return benefitConvert.convertToDto(currentBenefit);
    }



    @Override
    @Transactional
    public BenefitDTO delete(String id, Map<String, String> headers) {
        Optional<Benefit> optional=benefitRepository
                .findByUniqueIdentifierAndProductUniqueIdentifierAndProductCategoryUniqueIdentifier(id,
                        headers.get(ConstantsService.PRODUCT),headers.get(ConstantsService.CATEGORY));

        if (optional.isPresent()){
            benefitRepository.delete(optional.get());
            return benefitConvert.convertToDto(optional.get());
        }
        return  null;
    }

}
