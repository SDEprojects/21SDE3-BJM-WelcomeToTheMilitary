package com.dependapot.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<String, Item> inventory = new HashMap<>();

    public void addItem(Item item){
        if(!inventory.containsKey(item.getName())){
            inventory.put(item.getName(), item);
//            System.out.println("From Inventory class: " + this.inventory);
        }
    }

    // method to print existing items in inventory
    public void viewInventory() {
        if (inventory == null || inventory.size() == 0) {
            System.out.println("Your inventory is empty");
            return;
        }
        for (String eachKey : inventory.keySet()) {
            System.out.println("=".repeat(20));
            System.out.println("My Inventory");
            System.out.println(inventory.get(eachKey));
        }
    }

    public String checkInventory(){
        StringBuilder builder = new StringBuilder();
        List<Item> items = (List<Item>) inventory.values();
        for(Item item : items){
            builder.append(item.getName());
            if(item instanceof Weapons){
                Weapons weapon = (Weapons)item;
                Attributes attribute = weapon.getAt();
                builder.append(attribute.getStrength());
//                builder.append(attribute.getSpells());
//                builder.append(attribute.getHealth
            }
            builder.append(item.getAmount());
        }
        return builder.toString();
    }
}