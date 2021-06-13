package com.sb.ecommerce.infrastructure.api.dto;

import com.sb.ecommerce.domain.model.BackScratcherSize;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class BackScratcherDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Set<BackScratcherSize>  size;

}
