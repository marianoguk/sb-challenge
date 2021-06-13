package com.sb.ecommerce.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class BackScratcher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotBlank @Size(min = 1, max = 200) @Column(unique = true)
    private String name;
    @NotNull @NotBlank @Size(min = 1, max = 300)
    private String description;

    @NotNull @Min(value = 0, message = "Price should be a positive amount")
    private BigDecimal price;

    @ElementCollection(targetClass=BackScratcherSize.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="back_scratcher_size")
    @Column(name="size")
    private Set<BackScratcherSize> size;

    public BackScratcher with(Set<BackScratcherSize> size) {
        this.size = size;
        return this;
    }

    public BackScratcher with(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BackScratcher with(String description) {
        this.description = description;
        return this;
    }

}
