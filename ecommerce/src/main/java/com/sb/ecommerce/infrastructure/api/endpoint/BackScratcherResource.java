package com.sb.ecommerce.infrastructure.api.endpoint;

import com.sb.ecommerce.infrastructure.api.dto.BackScratcherDto;
import com.sb.ecommerce.domain.model.BackScratcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface BackScratcherResource {
    @Operation(summary = "Get a Back Scratcher by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Back Scratcher has been found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BackScratcher.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "404", description = "No Back Scratcher has been found", content = @Content)
    })
    ResponseEntity<BackScratcherDto> findById(@Parameter(description = "Back Scratcher Id")Long id);

    @Operation(summary = "Get all Back Scratchers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Back Scratcher are listed",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BackScratcher.class)
                            )
                    }
            )
    })
    ResponseEntity<List<BackScratcherDto>> findAll();

    @Operation(summary = "Back Scratcher creation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A Back Scratcher has been created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BackScratcher.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data for Back Scratcher creation",
                    content = @Content
            )
    })
    ResponseEntity<BackScratcherDto> create(
            @Parameter(description = "Back Scratcher data") @Valid BackScratcherDto dto
    );

    @Operation(summary = "Back Scratcher update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Back Scratcher has been updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BackScratcher.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data for Back Scratcher update",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Back Scratcher to be updated cannot be found by the given id",
                    content = @Content
            )
    })
    ResponseEntity<BackScratcherDto> update(
            @Parameter(description = "Back Scratcher data") @Valid BackScratcherDto dto
    );

    @Operation(summary = "Back Scratcher deletion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Back Scratcher has been deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BackScratcher.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Back Scratcher to be deleted cannot be found by the given id",
                    content = @Content
            )
    })
    ResponseEntity<BackScratcherDto> delete(@Parameter(description = "Back Scratcher Id using for deletion") Long id);
}
