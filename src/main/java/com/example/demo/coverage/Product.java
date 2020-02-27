package com.example.demo.coverage;

class Product {

    private Status status = Status.Available;

    Product(Status status) {
        this.status = status;
    }

    enum Status {
        Available, Bought

    }

    public boolean isAvailable() {
        if (status == Status.Available) {
            return true;
        } else {
            return false;
        }
    }

}
