package com.welcomeToTheMilitary.character;

import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.attributes.RetrieveSpecialHelper;
import com.welcomeToTheMilitary.gui.MainDisplay;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceMember implements java.io.Serializable{
    private String name;
    private String postName = "Fort Sill";
    public int healPotion;
    public int fitnessCounter;
    private String special = "Baking";
    private String location;
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
        // this should be update everytime the soldier get promotes
        this.health = 100;
        this.strength = 7;
        this.fitnessCounter = 0;
        pcsRequest = "No request (Type: request pcs to request a pcs)";
        healPotion = 5;
    }

    public ServiceMember() {

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

    public int getFitnessCounter() {
        return fitnessCounter;
    }

    public void setFitnessCounter(int fitnessCounter) {
        if (fitnessCounter > 0) {
            this.fitnessCounter = fitnessCounter;
        }

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

    public void setHealth(int health) {
        this.health = health;
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
    public void setStrength(int battleStrength, boolean dmgBoost){
        if(dmgBoost){
            this.strength += battleStrength;
        }else {
            this.strength -= battleStrength;
        }
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }



     //method to obtain item and store it in the inventory
    public void storeItemInVentory(Item itemName) {
        // System.out.println("Adding up the item: " + itemName);
        items.add(itemName);
    }


    //takes an Item parameter
    public void useItem(Item item) {
        //if consumable
        if (!this.items.contains(item)) {
            System.out.println("No item in your inventory");
            MainDisplay.setMainTextArea("No item in your inventory");
            return;
        }

        if (this.items.contains(item) && item.getType().equals("consumable")) {
            System.out.println("Using " + item.getName());
            MainDisplay.setMainTextArea("Using " + item.getName());
            setHealth(item.getValue(), true);
            items.remove(item);
        } else {
            //if weapon
            if (this.items.contains(item) && item.getType().equals("weapon")) {

                setStrength(item.getValue(),true);
                items.remove(item);
                System.out.println("Equipping item " + item.getName());
                System.out.println("This " + item.getName() + " will give me a damage boost!");
                MainDisplay.setMainTextArea("Equipping item " + item.getName() + "\n" +
                        "This " + item.getName() + " will give me a damage boost!");
            }
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
    }

    public int getNumberOfUserSpecialOnEachFinalBoss() {
        return NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS;
    }

    public void decreaseNumberOfUseSpecialOnEachFinalBoss() {
        if (NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS > 0) {
            NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS -= 1;
        }
    }

    public String listItemsForStats() {
        StringBuilder itemList = new StringBuilder();

        for (Item item : this.items) {
            itemList.append(item.getName()).append(" ").append("Value:").append(item.getValue()).append("\n");
        }

        return itemList.toString();
    }

    // use spell
    public int useSpecial() throws InterruptedException {

        // if the player can use the special
        String specialMove = MainDisplay.getSpecial();

        int increasedDamage = 0;
        switch(specialMove){

            case "Dog Tags":
                increasedDamage = 3;
                break;
            case "Military SkullTatoo":
                increasedDamage = 2;
                break;
            case "High-n-Tight":
                increasedDamage = 4;
                break;
            case "Punisher Hat":
                increasedDamage = 1;
                break;
        }

        Thread.sleep(2000);
        return this.getStrength() + increasedDamage;
    }

    @Override
    public String toString() {
        String inventory = "";
        if (this.items == null || this.items.isEmpty()) {
            inventory = "Inventory is empty";
        } else {
            inventory = this.items.toString();
        }
        return "=".repeat(5) + "Report Status" + " " + "=".repeat(5)
                + "\nName: " + this.getName() + "\nRank: " + this.getRank()
                + "\nSpecial " + this.getSpecial()
                + "\nHealth " + this.getHealth() + "\nStrength: " + this.getStrength()
                + "\nInventory: " + inventory + "\n" + "=".repeat(20);
    }

}
