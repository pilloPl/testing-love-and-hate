package com.example.demo.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {


    @Test
    @DisplayName("Every Product has a serial number")
    void everyProductHasASerialNumber() throws Exception {


    }

    @Test
    @DisplayName("Bought product is not available")
    void boughtProductShouldNotBeVisible() throws Exception {

    }

    @Test
    @DisplayName("Cannot change price of a bought product")
    void shouldNotChangePriceOfABoughtProduct() throws Exception {
        //given
        Product product = new Product("S_123");

        //and
        product.buy();

        //expect
        assertThrows(IllegalStateException.class, () -> product.changePrice(20));
    }

    @Test
    @DisplayName("Can change price of an available product")
    void shouldNotBeAbleToChangeDescriptionOfCancelledLeave() throws Exception {
        //given
        Product product = new Product("S_123");

        //and
        product.changePrice(10);

        //expect
        assertEquals(10, product.currentPrice());

    }






    @Test
    @DisplayName("can create a product which is just visible but not available")
    void newAddedProductCanBeVisibleButNotAvailable() throws Exception {
        //given
        Product product = new Product("JustInCatalogue_123");

        //expect
        assertFalse(product.isAvailable());

    }






}