package com.sb.ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BackScratcherSize {
    S(1), M(2), XM(3), L(4), XL(5);

    private int orderSort;
}
