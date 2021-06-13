package com.sb.ecommerce.domain.service;

import com.sb.ecommerce.domain.model.BackScratcher;
import com.sb.ecommerce.infrastructure.api.dto.BackScratcherDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BackScratcherMapper {

    BackScratcher map(BackScratcherDto source);
    BackScratcherDto map(BackScratcher source);
}