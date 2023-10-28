package com.dojo.product.service.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Table(name = "benefits")
@Data
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unique_identifier")
    private String uniqueIdentifier;
    @NotBlank
    private String description;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
