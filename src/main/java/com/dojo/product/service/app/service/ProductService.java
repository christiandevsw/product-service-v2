package com.dojo.product.service.app.service;

import com.dojo.product.service.app.dto.BasicProductDTO;
import com.dojo.product.service.app.dto.DetailProductDTO;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Integer totalProducts();
    List<BasicProductDTO> getAllProducts();
    List<BasicProductDTO> listProductsByCategory(String categoryId);
    List<BasicProductDTO> getProductsByPrice(BigDecimal price);
    List<BasicProductDTO> listProductsByNameAndCategory(String name, String categoryId);
    DetailProductDTO getProductByIdentifier(String id);
    DetailProductDTO retrieveBenefits(DetailProductDTO dto);

    Map<String,Object> verifyProductIfExistsByIdentifier(String id);
    DetailProductDTO create(DetailProductDTO detailProductDTO);
    DetailProductDTO update(DetailProductDTO detailProductDTO,String identifier) throws DataAccessException;
    BasicProductDTO deleteProduct(String id,Map<String,String> headers);

}
