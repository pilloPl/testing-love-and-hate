package com.example.demo.coverage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.demo.coverage.Product.Status.Available;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {


    @Test
    void new_product_is_available() throws Exception {

        Product p = new Product(Available);

        assertTrue(p.isAvailable());


    }


}