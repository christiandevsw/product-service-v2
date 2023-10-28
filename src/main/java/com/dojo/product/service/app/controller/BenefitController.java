package com.dojo.product.service.app.controller;

import com.dojo.product.service.app.dto.BenefitDTO;
import com.dojo.product.service.app.service.BenefitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("benefits")
public class BenefitController {
    private BenefitService benefitService;

    @PostMapping
    public ResponseEntity<?> newBenefit(@Valid @RequestBody BenefitDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> mistakes = new HashMap<>();
            result.getFieldErrors().forEach(error -> mistakes.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage()));
            return new ResponseEntity<Map<String, Object>>(mistakes, HttpStatus.BAD_REQUEST);
        }

        BenefitDTO benefitDTO;
        try {
            benefitDTO = benefitService.save(dto);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error al intentar guardar en la BBDD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (benefitDTO == null)
            return new ResponseEntity<String>("El producto al que pertenece el beneficio no existe en la BBDD", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<BenefitDTO>(benefitDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{uniqueIdentifier}")
    public ResponseEntity<?> updateBenefit(@Valid @RequestBody BenefitDTO dto, BindingResult result, @PathVariable String uniqueIdentifier) {
        if (result.hasErrors()) {
            Map<String, Object> mistakes = new HashMap<>();
            result.getFieldErrors().forEach(error -> mistakes.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage()));
            return new ResponseEntity<Map<String, Object>>(mistakes, HttpStatus.BAD_REQUEST);
        }

        BenefitDTO benefitDTO;
        try {
            benefitDTO = benefitService.update(uniqueIdentifier, dto);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error al intentar actualizar en la BBDD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (benefitDTO == null)
            return new ResponseEntity<String>("El beneficio no existe en la BBDD", HttpStatus.NOT_FOUND);

        return new ResponseEntity<BenefitDTO>(benefitDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("{uniqueIdentifier}")
    public ResponseEntity<?> deleteBenefit(@PathVariable String uniqueIdentifier, @RequestHeader Map<String, String> headers) {
        BenefitDTO benefitDTO;
        try {
            benefitDTO = benefitService.delete(uniqueIdentifier, headers);
        } catch (DataAccessException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", e.getMostSpecificCause().getMessage());
            map.put("message", "Ocurrió un error al intentar actualizar en la BBDD");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (benefitDTO == null)
            return new ResponseEntity<String>("No existe el beneficio en la BBDD", HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>("Se eliminó correctamente el producto con id: " + uniqueIdentifier, HttpStatus.OK);
    }

}
