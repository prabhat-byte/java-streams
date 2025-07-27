package com.example.oops;

public class MainClass {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
//        Item item = new Item("Generic Item", 20);
        Fruit fruit = new Fruit("Apple", 2, "fuji");
        Weapon weapon = new Weapon("Sword", 2, 75, "Melee");

//        inventory.addItem(item);
        inventory.addItem(fruit);
        inventory.addItem(weapon);
        inventory.displayInventory();
    }

}
