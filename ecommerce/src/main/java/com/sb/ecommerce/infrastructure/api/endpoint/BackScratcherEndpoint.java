package com.sb.ecommerce.infrastructure.api.endpoint;

import com.sb.ecommerce.domain.service.BackScratcherService;
import com.sb.ecommerce.infrastructure.api.dto.BackScratcherDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/back-scratcher")
public class BackScratcherEndpoint implements BackScratcherResource {
    private final BackScratcherService service;

    public BackScratcherEndpoint(BackScratcherService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BackScratcherDto> findById(@PathVariable Long id) {
        var res = service.findById(id);
        if (res.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(res.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<BackScratcherDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<BackScratcherDto> create(@RequestBody BackScratcherDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<BackScratcherDto> update(@RequestBody BackScratcherDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BackScratcherDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}