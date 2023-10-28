package com.dojo.product.service.app.service;

import com.dojo.product.service.app.dto.CategoryDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> listCategories();
    CategoryDTO getById(String id);
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO,String id) throws DataAccessException;
    void deleteById(String id);

}
