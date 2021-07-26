package com.welcomeToTheMilitary.bases;

import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.gui.mainDisplay;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseMap {

    //fields
    private String name;
    private String description;
    private ArrayList<Item> items = null;
    private ArrayList<String> buildings;
    private ArrayList<Enlisted> soldiers;


    public BaseMap() throws IOException, ParseException {
        setItems();
        setBuildings();
        setSoldiers();
    }

    public BaseMap(String name, String description) throws IOException, ParseException {
        this.name = name;
        setItems();
        setBuildings();
        setSoldiers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public void setItems() throws IOException, ParseException {
        ArrayList<Item> initialItems = JsonReader.getItems();
        ArrayList<Item> itemsArray = new ArrayList<>();
        for(Item item : initialItems){
            if(item.getMap().equals(name)){
                itemsArray.add(item);
            }
        }
        this.items = itemsArray;
    }

    public ArrayList<String> getBuildings() {
        return buildings;
    }

    public void setBuildings() throws IOException, ParseException{
        this.buildings = JsonReader.getBuildingStrings(name);
    }

    public ArrayList<Enlisted> getSoldiers() {
        return soldiers;
    }

    public ArrayList<Enlisted> getSoldiers(String currentLocation) {
        ArrayList<Enlisted> soldiersInBuilding = new ArrayList<>();
        for(Enlisted enlisted : soldiers){
            if(enlisted.getLocation().equals(currentLocation)){
                soldiersInBuilding.add(enlisted);
            }
        }
        return soldiersInBuilding;
    }

    public void setSoldiers() throws IOException, ParseException {
        this.soldiers = JsonReader.getSoldiers().get(name);
    }

    public void displaySoldiers(String currentLocation) throws IOException, ParseException {
        StringBuilder soldierList = new StringBuilder();
        soldierList.append("Entering ").append(currentLocation).append(" building!").append("\n");
        soldierList.append("_".repeat(45)).append("\n");
        for(Enlisted enlisted : soldiers){
            if(enlisted.getLocation().equals(currentLocation)){
                System.out.println(enlisted);
                soldierList.append(enlisted).append("\n").append("_".repeat(45));
            }
        }
        mainDisplay.setMainTextArea(soldierList.toString());
    }

    public void displayItems(String currentLocation) {
        for (Item i : items) {
            if (i.getLocation().equals(currentLocation)) {
                System.out.println(i);
            }
        }
    }



    public void removeOneItem(String item, String currentLocation) {
        Item removeItem = null;
        for (Item i : items) {
            if (i.getLocation().equals(currentLocation)) {
                removeItem = i;
            }
        }
        items.remove(removeItem);
    }
    public Item getCurrentItem(String currentLocation) {
        Item currentItem = null;

        for (Item i : items) {
            if (i.getLocation().equals(currentLocation)) {
                currentItem = i;
            }
        }
        if (currentItem != null ){
            removeOneItem(currentItem.getName(), currentLocation);
        }
        System.out.println("Obtained Item: " + currentItem.getName() + "!");
        return currentItem;
    }

    public String listBuildingsForMap() {
        StringBuilder buildList = new StringBuilder();

        for (String building : this.buildings) {
            buildList.append(building).append("\n");
        }

        return buildList.toString();
    }

}
