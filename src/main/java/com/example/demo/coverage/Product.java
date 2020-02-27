package com.example.demo.coverage;

class Product {

    private Status status = Status.Available;

    enum Status {
        Available, Bought

    }

    public boolean isAvailable() {
        return status == Status.Available;
    }

}
