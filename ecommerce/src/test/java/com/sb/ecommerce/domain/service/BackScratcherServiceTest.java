package com.sb.ecommerce.domain.service;

import com.sb.ecommerce.domain.model.BackScratcherSize;
import com.sb.ecommerce.it.AbstractIt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class BackScratcherServiceTest extends AbstractIt {
    @Autowired
    private BackScratcherService service;

    @Test
    void create() {
        var dto = BackScratcherBuilder.create("S1", Set.of(BackScratcherSize.S));
        var created = service.create(dto);
        Assertions.assertNotNull(created.getId());
    }

}
