package com.example.oops;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> Items;

    public Inventory() {
        Items = new ArrayList<>();
    }

    public void addItem(Item item) {
        Items.add(item);
    }

    public void displayInventory() {
        for (Item item : Items) {
            System.out.println(item.displayInfo());
        }
    }
}
