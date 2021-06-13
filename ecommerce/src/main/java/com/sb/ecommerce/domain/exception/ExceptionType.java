package com.sb.ecommerce.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionType {
    BACK_SCRATCHER_SIZE_DELETION("Back Scratcher Size", "Deletion"),
    BACK_SCRATCHER_CREATION("Back Scratcher", "Creation"),
    BACK_SCRATCHER_CREATION_DUPLICATED_NAME("Back Scratcher", "Creation"),
    BACK_SCRATCHER_EDITION("Back Scratcher", "Edition"),
    BACK_SCRATCHER_NOT_FOUND("Back Scratcher", "Not Found"),
    BACK_SCRATCHER_DELETION("Back Scratcher", "Deletion"),
    BACK_SCRATCHER_UNEXPECTED("Back Scratcher", "Unexpected");
    private String entity;
    private String type;
}
