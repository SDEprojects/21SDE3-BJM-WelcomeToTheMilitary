package com.welcomeToTheMilitary.character;

import com.welcomeToTheMilitary.attributes.Inventory;
import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.attributes.RetrieveSpecialHelper;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceMember {
    private String name;
    private String postName = "Fort Sill";
    public int healPotion;
    public int level;
    private String special = "Baking";
    private String location;
    private Inventory inventory = null;
    private Rank rank = null;
    private int health = 0;
    private int strength = 0;

    //arraylist of Items
    private ArrayList<Item> items = new ArrayList<>();

    // private Item items = null;
    private String pcsRequest = null;
    // number of uses of spell
    private static int NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS = 3;

    // ServiceMember Constructor that pulls from Character class
    public ServiceMember(String _name, String _special, String _location) throws IOException, ParseException {
        setName(_name);
        this.special = _special;
        this.location = _location;
        this.rank = Rank.E1; // initial rank
        inventory = new Inventory();
        // this should be update everytime the soldier get promotes
        this.health = 100;
        this.strength = 5;
        pcsRequest = "No request (Type: request pcs to request a pcs)";
        healPotion = 5;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getHealPotion() {
        return this.healPotion;
    }

    public void setHealPotion() {
        this.healPotion -= 1;
    }

    public String getPcsRequest() {
        return this.pcsRequest;
    }

    public void setPcsRequest(String _pcsRequest) {
        this.pcsRequest = _pcsRequest;
    }

    public String getName(){
        return name;
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

    public Rank getRank() {
        return this.rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    // this has to update service member's health and attack damage
    // will be modify with game controller
    public void setRank(Rank _rank) {
        // each level up health += 2
        // each level up strength += 2
        this.setHealth(  2, true);
        this.setStrength( this.getStrength()  + 2);
        this.rank = _rank;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int _health, boolean isHeal) {
        if (isHeal) {
            this.health += _health;
        } else {
            this.health -= _health;
        }
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int _strength) {
        this.strength = _strength;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


    public Map<String, Item> getInventory() {
        return this.inventory.getInventory();
    }

     //method to obtain item and store it in the inventory
    public void storeItemInVentory(Item itemName) {
        // System.out.println("Adding up the item: " + itemName);
        items.add(itemName);
    }

    public void viewMyInventory() {
        inventory.viewInventory();
    }

    //takes an Item parameter
    public void useItem(Item item) {
        //if consumable
        if(!this.items.contains(item)){
            System.out.println("No item in your inventory");
            return;
        }

        if(this.items.contains(item) && item.getType().equals("consumable")){
            System.out.println("Using " + item.getName());
            setHealth(item.getValue(), true);
            items.remove(item);
            return;
        }
        //if weapon
        if(this.items.contains(item)){
            System.out.println("Using " + item.getName());
        }
    }

    //takes a string parameter for user input in the GameController class
    public void useItem(String item) {
        Item itemToUse = null;
        for(Item itemInList : this.items){
            if(itemInList.getName().equals(item)){
                itemToUse = itemInList;
            }
        }
        useItem(itemToUse);
    }

    public int attack() {
//        System.out.println("Attack: " + this.getStrength());
        return this.getStrength();
    }

    // setter to set Number of special the player can use to fight against boss
    public void setNumberOfUseSpecialOnEachFinalBoss() {
        NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS = 3;
        return;
    }

    public int getNumberOfUserSpecialOnEachFinalBoss() {
        return NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS;
    }

    public void decreaseNumberOfUseSpecialOnEachFinalBoss() {
        if (NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS > 0) {
            NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS -= 1;
            return;
        }
    }

    // use spell
    public int useSpecial() {
        if (NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS <= 0) {
            System.out.println("You cannot use spell anymore");
            System.out.println("Attacking the boss with the normal strength");
            return this.getStrength();
        }
        // if the player can use the special
        boolean isSpecialUseAbleFlag = false;
        HashMap<String, String> specialHash = RetrieveSpecialHelper.getSpecialName();
        // System.out.println(this.getSpecial());
        // make the key set into String array
        String[] possibleSpecial = specialHash.keySet().toArray(new String[0]);
        for (int i = 0; i < possibleSpecial.length; i++) {
            if (possibleSpecial[i].equals(this.getSpecial())) {
                System.out.println("Using my speciality! " + getSpecial() + specialHash.get(getSpecial()));
                isSpecialUseAbleFlag = true;
            }
        }
        if (isSpecialUseAbleFlag) {
            this.decreaseNumberOfUseSpecialOnEachFinalBoss();
            return this.getStrength() + RetrieveSpecialHelper.getSpecialDamage(getSpecial());
        }
        System.out.println("The special you typed does not exist");
        System.out.println("Attacking the boss with the normal strength");
        return this.getStrength();
    }

    @Override
    public String toString() {
        String inventory = "";
        if (this.inventory == null || this.inventory.isInventoryEmpty()) {
            inventory = "Inventory is empty";
        } else {
            inventory = this.inventory.toString();
        }
        return "=".repeat(5) + "Report Status" + " " + "=".repeat(5)
                + "\nName: " + this.getName() + "\nRank: " + this.getRank()
                + "\nSpecial " + this.getSpecial()
                + "\nHealth " + this.getHealth() + "\nStrength: " + this.getStrength()
                + "\nInventory: " + inventory + "\n" + "=".repeat(20);
    }

}
