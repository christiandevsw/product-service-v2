package com.dojo.product.service.app.service.impl;

import com.dojo.product.service.app.dto.CategoryDTO;
import com.dojo.product.service.app.entity.Category;
import com.dojo.product.service.app.repository.CategoryRepository;
import com.dojo.product.service.app.service.CategoryService;
import com.dojo.product.service.app.service.other.impl.CategoryConvertService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryConvertService serviceConvert;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> listCategories() {
        return categoryRepository.findAll().stream().map(serviceConvert::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getById(String id) {
        Optional<Category> optional = categoryRepository.findByUniqueIdentifier(id);
        if (optional.isPresent()) return serviceConvert.convertToDto(optional.get());
        return null;
    }

    @Transactional
    public CategoryDTO create(CategoryDTO dto) {
        dto.setId(UUID.randomUUID().toString());
        Category newCategory = categoryRepository.save(serviceConvert.convertToEntity(dto));
        return serviceConvert.convertToDto(newCategory);
    }

    @Override
    @Transactional
    public CategoryDTO update(CategoryDTO categoryDTO, String id) throws DataAccessException {
        Optional<Category> optional = categoryRepository.findByUniqueIdentifier(id);
        if (optional.isEmpty()) return null;

        Category currentCategory = optional.get();
        currentCategory.setName(categoryDTO.getName());
        currentCategory.setDescription(categoryDTO.getDescription());
        if (categoryDTO.getPhoto() != null) {
            currentCategory.setPhoto(categoryDTO.getPhoto());
        }
        return serviceConvert.convertToDto(categoryRepository.save(currentCategory));

    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Optional<Category> optional = categoryRepository.findByUniqueIdentifier(id);
        if (optional.isPresent()) {
            categoryRepository.delete(optional.get());
        }
    }


}
