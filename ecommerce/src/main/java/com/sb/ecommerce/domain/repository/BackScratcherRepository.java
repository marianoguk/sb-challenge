package com.sb.ecommerce.domain.repository;

import com.sb.ecommerce.domain.model.BackScratcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BackScratcherRepository extends JpaRepository<BackScratcher, Long> {
    BackScratcher findByName(String name);
    @Query("select case when count(bs)> 0 then true else false end from BackScratcher bs where lower(bs.name) = :name")
    boolean existByName(@Param("name") String name);
}
