package com.dojo.product.service.app.service.impl;

import com.dojo.product.service.app.dto.BasicProductDTO;
import com.dojo.product.service.app.dto.BenefitDTO;
import com.dojo.product.service.app.dto.DetailProductDTO;
import com.dojo.product.service.app.entity.Benefit;
import com.dojo.product.service.app.entity.Category;
import com.dojo.product.service.app.entity.Product;
import com.dojo.product.service.app.repository.BenefitRepository;
import com.dojo.product.service.app.repository.CategoryRepository;
import com.dojo.product.service.app.repository.ProductRepository;
import com.dojo.product.service.app.service.ProductService;
import com.dojo.product.service.app.service.other.impl.BasicProductConvertService;
import com.dojo.product.service.app.service.other.impl.BenefitConvertService;
import com.dojo.product.service.app.service.other.impl.DetailProductConvertService;
import com.dojo.product.service.app.util.ConstantsService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private BenefitRepository benefitRepository;
    private BasicProductConvertService basicProductConvert;
    private DetailProductConvertService detailProductConvert;
    private BenefitConvertService benefitConvert;


    @Override
    @Transactional(readOnly = true)
    public Integer totalProducts() {
        return productRepository.getTotalProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasicProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(basicProductConvert::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasicProductDTO> listProductsByCategory(String categoryId) {
        return productRepository.getProductsByCategory(categoryId).stream()
                .map(basicProductConvert::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasicProductDTO> getProductsByPrice(BigDecimal price) {
        return productRepository.findByPrice(price.intValue()).stream().map(basicProductConvert::convertToDto
        ).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public List<BasicProductDTO> listProductsByNameAndCategory(String name, String categoryId) {
        return productRepository.findByNameAndCategory(name, categoryId).stream()
                .map(basicProductConvert::convertToDto).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public DetailProductDTO getProductByIdentifier(String id) {
        Optional<Product> optionalProduct = productRepository.findByUniqueIdentifier(id);
        if (optionalProduct.isPresent()) {
            return detailProductConvert.convertToDto(optionalProduct.get());
        }
        return null;
    }

    @Override
    public DetailProductDTO retrieveBenefits(DetailProductDTO dto) {
        Product product=productRepository.findByUniqueIdentifier(dto.getId()).get();
        Set<Benefit> benefits=benefitRepository.findByProductId(product.getId());
        if (benefits.size()>0) {
            Set<BenefitDTO> benefitsDTO=benefits.stream().map(b -> benefitConvert.convertToDto(b)).collect(Collectors.toSet());
            dto.setBenefits(benefitsDTO);
        }
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> verifyProductIfExistsByIdentifier(String id) {
        Optional<Product> optionalProduct = productRepository.findByUniqueIdentifier(id);
        if (optionalProduct.isPresent()) {
            Map<String, Object> map = new HashMap<>();
            map.put("product", basicProductConvert.convertToDto(optionalProduct.get()));
            map.put("availability", optionalProduct.get().getAvailable());
            return map;
        }
        return null;
    }

    @Override
    @Transactional
    public DetailProductDTO create(DetailProductDTO dto) {
        dto.setId(UUID.randomUUID().toString());
        if (dto.getDscto() == null) dto.setDscto(BigDecimal.ZERO);
        if (dto.getCategory() != null) {

            Optional<Category> optional = categoryRepository.findByUniqueIdentifier(dto.getCategory().getId());
            if (optional.isEmpty()) return null;
            Product newProduct = detailProductConvert.convertToEntity(dto);
            newProduct.setCategory(optional.get());

            return detailProductConvert.convertToDto(productRepository.save(newProduct));
        }
        return null;
    }

    @Override
    public DetailProductDTO update(DetailProductDTO dto, String identifier) throws DataAccessException {

        if (dto.getCategory() != null) {
            Optional<Category> optionalCategory = categoryRepository.findByUniqueIdentifier(dto.getCategory().getId());
            if (optionalCategory.isEmpty()) return null;

            Optional<Product> optional = productRepository.findByUniqueIdentifier(identifier);
            if (optional.isEmpty()) return null;
            Product currentProduct = optional.get();
            currentProduct.setName(dto.getName());
            currentProduct.setPrice(dto.getPrice());
            currentProduct.setDescription(dto.getDescription());
            currentProduct.setStock(dto.getStock());
            currentProduct.setAvailable(dto.getAvailable());
            currentProduct.setCategory(optionalCategory.get());
            currentProduct.setDscto(dto.getDscto());
            currentProduct.setPhoto(dto.getPhoto());
            return detailProductConvert.convertToDto(productRepository.save(currentProduct));
        }
        return null;
    }

    @Override
    @Transactional
    public BasicProductDTO deleteProduct(String identifier, Map<String, String> headers) {
        Optional<Product> optional = productRepository
                .findByUniqueIdentifierAndCategoryUniqueIdentifier(identifier, headers.get(ConstantsService.CATEGORY));
        if (optional.isPresent()) {
            productRepository.delete(optional.get());
            return basicProductConvert.convertToDto(optional.get());
        }
        return null;
    }
}
