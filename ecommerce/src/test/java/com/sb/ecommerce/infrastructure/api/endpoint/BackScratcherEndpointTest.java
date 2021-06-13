package com.sb.ecommerce.infrastructure.api.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.ecommerce.EcommerceApplication;
import com.sb.ecommerce.domain.exception.DomainException;
import com.sb.ecommerce.domain.exception.ExceptionType;
import com.sb.ecommerce.domain.model.BackScratcherSize;
import com.sb.ecommerce.domain.service.BackScratcherBuilder;
import com.sb.ecommerce.infrastructure.api.dto.BackScratcherDto;
import com.sb.ecommerce.it.H2JpaTestConfig;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(classes = {EcommerceApplication.class, H2JpaTestConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("inmemorydb")
public class BackScratcherEndpointTest {

    private static final String PATH = "/api/v1/back-scratcher";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @SneakyThrows
    @Test
    public void createOk() {
        var toBeCreated = BackScratcherBuilder.create();
        ResultActions result = doCreate(toBeCreated).andExpect(status().isCreated());
        var created= extractDto(result);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals(created.getName(), toBeCreated.getName());
        assertEquals(created.getSize(), toBeCreated.getSize());
        assertEquals(created.getDescription(), toBeCreated.getDescription());
        assertEquals(created.getPrice(), toBeCreated.getPrice());
    }

    @SneakyThrows
    @Test
    public void createDuplicated() {
        var toBeCreated = BackScratcherBuilder.create();
        // First creation --> Ok
        doCreate(toBeCreated);
        // Duplicated creation --> Failure
        ResultActions result = doCreate(toBeCreated).andExpect(status().isBadRequest());

        assertEquals(DomainException.class, result.andReturn().getResolvedException().getClass());
        var actual = (DomainException) result.andReturn().getResolvedException();
        assertEquals(ExceptionType.BACK_SCRATCHER_CREATION_DUPLICATED_NAME, actual.getType());
    }

    @Test
    @SneakyThrows
    public void deleteOk() {
        var created = BackScratcherBuilder.create();
        Long createdId = extractDto(doCreate(created)).getId();
        doFindById(createdId).andExpect(status().isOk());
        var result = doDelete(createdId);
        result.andExpect(status().isOk());
        doFindById(createdId).andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    public void deleteNoExists() {
        Long toBeDeletedId = 10L;
        doFindById(toBeDeletedId).andExpect(status().isNotFound());
        var result = doDelete(toBeDeletedId);
        result.andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    public void updateOK() {
        var created = extractDto(doCreate(BackScratcherBuilder.create()));
        var update = new BackScratcherDto();
        update.setId(created.getId());
        update.setName(created.getName());
        update.setPrice(created.getPrice().multiply(BigDecimal.TEN));
        update.setDescription(created.getDescription() + "[UPDATED]");
        update.setSize(Set.of(BackScratcherSize.values()));
        var result = doUpdate(update);
        result.andExpect(status().isOk());
        assertEquals(update, extractDto(result));
    }

    @Test
    @SneakyThrows
    public void updateFailureNullName() {
        var created = extractDto(doCreate(BackScratcherBuilder.create()));
        var update = new BackScratcherDto();
        update.setId(created.getId());
        update.setName(null);
        update.setPrice(created.getPrice().multiply(BigDecimal.TEN));
        update.setDescription(created.getDescription() + "[UPDATED]");
        update.setSize(Set.of(BackScratcherSize.values()));
        var result = doUpdate(update);
        result.andExpect(status().isBadRequest());
    }

    @SneakyThrows
    private ResultActions doCreate(BackScratcherDto body) {
        return mockMvc.perform(
                post(PATH).contentType(APPLICATION_JSON).content(mapper.writeValueAsBytes(body))
        );
    }


    @SneakyThrows
    private ResultActions doUpdate(BackScratcherDto body) {
        return mockMvc.perform(
                put(PATH).contentType(APPLICATION_JSON).content(mapper.writeValueAsBytes(body))
        );
    }

    @SneakyThrows
    private ResultActions doDelete(Long id) {
        return mockMvc.perform(delete(PATH+"/"+id));
    }

    @SneakyThrows
    private ResultActions doFindById(Long id) {
        return mockMvc.perform(get(PATH+"/"+id));
    }

    @SneakyThrows
    private BackScratcherDto extractDto(ResultActions result) {
        return mapper.readValue(result.andReturn().getResponse().getContentAsString(), BackScratcherDto.class);
    }
}
