package com.sb.ecommerce.domain.service;

import com.sb.ecommerce.domain.exception.DomainException;
import com.sb.ecommerce.domain.exception.ExceptionType;
import com.sb.ecommerce.domain.repository.BackScratcherRepository;
import com.sb.ecommerce.infrastructure.api.dto.BackScratcherDto;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BackScratcherService {
    private final BackScratcherRepository repository;
    private final BackScratcherMapper mapper;
    private final Validator validator;

    public BackScratcherService(
            BackScratcherRepository repo,  BackScratcherMapper mapper, Validator validator) {
        this.repository = repo;
        this.mapper = mapper;
        this.validator = validator;
    }

    public Optional<BackScratcherDto> findByName(String name) {
        return Optional.ofNullable(mapper.map(repository.findByName(name)));
    }

    public List<BackScratcherDto> findAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    public BackScratcherDto create(BackScratcherDto dto) {
        if (repository.existByName(dto.getName())) {
            throw new DomainException(ExceptionType.BACK_SCRATCHER_CREATION_DUPLICATED_NAME);
        }

        var model = mapper.map(dto);
        var validation = validator.validate(model);
        if(validation.isEmpty()) {
            return mapper.map(repository.save(model));
        }
        throw new DomainException(ExceptionType.BACK_SCRATCHER_CREATION);
    }

    @Transactional
    public BackScratcherDto update(BackScratcherDto dto) {

        var model = repository.findById(dto.getId());
        if(model.isEmpty()) {
            throw new DomainException(ExceptionType.BACK_SCRATCHER_NOT_FOUND);
        }

        model.get().edit(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getSize());

        var validation = validator.validate(model.get());
        if(validation.isEmpty()) {
            return mapper.map(repository.save(model.get()));
        }
        throw new DomainException(ExceptionType.BACK_SCRATCHER_EDITION);
    }

    public void delete(Long id) {
        var model = repository.findById(id);
        if(model.isEmpty()) {
            throw new DomainException(ExceptionType.BACK_SCRATCHER_NOT_FOUND);
        }
        repository.delete(model.get());
    }

    public Optional<BackScratcherDto> findById(Long id) {
        return Optional.ofNullable(mapper.map(repository.findById(id).orElse(null)));
    }
}