package com.dojo.product.service.app.dto.util;

import com.dojo.product.service.app.dto.CategoryDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CategoryDTOSerializer extends JsonSerializer<CategoryDTO> {
    @Override
    public void serialize(CategoryDTO dto, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(dto.getName());
    }
}
