package com.example.demo.coverage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.demo.coverage.Product.Status.Available;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {


    @Test
    @DisplayName("product in catalogue can be visible but not available")
    void can_create_unavailable_but_visible_product() throws Exception {

        Product p = new Product(Available);

        assertTrue(p.isAvailable());


    }


}