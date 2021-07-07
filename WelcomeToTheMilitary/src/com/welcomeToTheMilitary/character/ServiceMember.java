package com.welcomeToTheMilitary.character;

import com.welcomeToTheMilitary.attributes.Inventory;
import com.welcomeToTheMilitary.attributes.Item;

public class ServiceMember extends Character {
    public int heal;
    public int level;
    private String special = "Baking";
    private String location;
    private Inventory inventory = null;
    private String rank = null;

// ServiceMember Constructor that pulls from Character class
    public ServiceMember(String _name, String _special, String _location ) {
        super(_name);
        this.special = _special;
        this.location = _location;
        this.rank = "fuzzy"; // initial rank
        inventory = new Inventory();
    }
    public String getName(){
        return super.getName();
    }
    public String getSpecial(){
        return special;
    }
    public String getLocation(){
        return location;
    }
    public void  setLocation(String _location){
        this.location = _location;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String _rank) {
        this.rank = _rank;
    }
    // method to obtain item and store it in the inventory
    public void storeItemInVentory(String itemName) {
        System.out.println("Adding up the item: " + itemName);
        inventory.addItem(new Item(itemName));
    }

    public void viewMyInventory() {
        inventory.viewInventory();
    }

    public int getHealth(String name, int heal) {
       //super(name);
        this.heal = heal;
        //this.type = "Consumable";
        return heal;
    }



}
