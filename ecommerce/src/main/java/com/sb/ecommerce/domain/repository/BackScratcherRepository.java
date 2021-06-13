package com.sb.ecommerce.domain.repository;

import com.sb.ecommerce.domain.model.BackScratcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackScratcherRepository extends JpaRepository<BackScratcher, Long> {
    BackScratcher findByName(String name);
}
