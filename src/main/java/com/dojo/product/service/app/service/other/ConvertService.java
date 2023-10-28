package com.dojo.product.service.app.service.other;

public interface ConvertService<E,D> {
    E convertToEntity(D t);
    D convertToDto(E t);
}
