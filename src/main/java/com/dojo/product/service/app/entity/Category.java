package com.dojo.product.service.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unique_identifier")
    private String uniqueIdentifier;
    @NotBlank
    @Size(min = 4,max = 20)
    private String name;
    @NotBlank
    @Size(min = 20,max = 255)
    private String description;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;
}
