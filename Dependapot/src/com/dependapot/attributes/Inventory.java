package com.dependapot.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<String, Item> inventory;

//    public HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();

//    public Inventory(Create owner){
//        this.inventory = new HashMap<String, Item>();
//    }

    public boolean addItem(Item item){
        if(!inventory.containsKey(item.getName())){
            inventory.put(item.getName(), item);
            return true;
        }
//    else if(inventory.containsKey(item.getName()) && item.isStackable()){
//            inventory.get(item.getName()).addToStack();
//            return true;
//        }
        return false;
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