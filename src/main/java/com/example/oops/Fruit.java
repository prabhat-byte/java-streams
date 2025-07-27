package com.example.oops;

public class Fruit extends Item {

    private String type;

    public Fruit(String name, int quantity, String type) {
        super(name, quantity);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String displayInfo() {
        return "Fruit{" +
                "name='" + getName() + '\'' +
                ", quantity='" + getQuantity() + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + getName() + '\'' +
                ", quantity='" + getQuantity() + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
