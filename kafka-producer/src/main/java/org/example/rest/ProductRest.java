package org.example.rest;

import org.example.model.Product;
import org.example.producer.ProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/store")
public class ProductRest {

    @Autowired
    private ProductProducer productProducer;

    @GetMapping(value = "/send")
    public void send() {

        var product = new Product();
        product.setPrice(BigDecimal.TEN);
        product.setName("Laptop");
        product.setHashId(UUID.randomUUID().toString());
        productProducer.send(product);
    }
}
