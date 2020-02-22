package com.example.demo.tdd;

class Product {

    Product(String s_123) {
        this.serialNumber = s_123;
    }

    enum Status {Available, Bought}

    private final String serialNumber;
    private Status status = Status.Available;
    private int price;

    public void buy() {
        this.status = Status.Bought;
    }

    public boolean isAvailable() {
        return status == Status.Available && !serialNumber.contains("JustInCatalogue");
    }

    public int currentPrice() {
        return price;
    }

    public void changePrice(int i) {
        if(isBought()) {
            throw new IllegalStateException();
        }
        this.price = i;
    }

    private boolean isBought() {
        return status == Status.Bought;
    }
}

class ProductDefinition {
    private int price;
}
