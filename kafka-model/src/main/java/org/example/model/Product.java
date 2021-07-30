package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private String name;
    private BigDecimal price;
    private String hashId;
}
