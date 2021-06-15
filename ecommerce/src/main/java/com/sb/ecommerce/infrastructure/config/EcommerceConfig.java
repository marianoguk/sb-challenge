package com.sb.ecommerce.infrastructure.config;

import com.sb.ecommerce.domain.repository.BackScratcherRepository;
import com.sb.ecommerce.domain.service.BackScratcherMapper;
import com.sb.ecommerce.domain.service.BackScratcherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validator;

@Configuration
public class EcommerceConfig {

    @Bean
    public BackScratcherService backScratcherService(
            BackScratcherRepository repo, BackScratcherMapper mapper, Validator validator) {
        return new BackScratcherService(repo, mapper, validator);
    }

}
