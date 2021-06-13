package com.sb.ecommerce.domain.service;

import com.sb.ecommerce.domain.model.BackScratcherSize;
import com.sb.ecommerce.infrastructure.api.dto.BackScratcherDto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class BackScratcherBuilder {
    public static BackScratcherDto create(String name, Set<BackScratcherSize> size) {
        BackScratcherDto dto = new BackScratcherDto();
        dto.setName(name);
        dto.setSize(size);
        dto.setDescription("Description for " + name);
        dto.setPrice(BigDecimal.TEN);
        return dto;
    }

    public static BackScratcherDto create() {
        String randomName = UUID.randomUUID().toString().substring(0, 15);
        return create(randomName, Set.of(BackScratcherSize.S, BackScratcherSize.M));
    }
}
