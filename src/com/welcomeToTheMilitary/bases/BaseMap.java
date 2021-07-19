package com.welcomeToTheMilitary.bases;

import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    //fields
    private String name;
    private String description;
    private ArrayList<Item> items = null;
    private ArrayList<String> buildings;
    private ArrayList<Enlisted> soldiers;


    public Map() throws IOException, ParseException {
        setItems();
        setBuildings();
        setSoldiers();
    }

    public Map(String name) throws IOException, ParseException {
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

    public void setBuildings() throws IOException, ParseException {
        ArrayList<String> locations = JsonReader.getBuildingStrings(name);
        this.buildings = locations;
    }

    public ArrayList<Enlisted> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers() throws IOException, ParseException {
        this.soldiers = JsonReader.getSoldiers().get(name);
    }

    public void displaySoldiers(String currentLocation) throws IOException, ParseException {
        for(Enlisted enlisted : soldiers){
            if(enlisted.getLocation().equals(currentLocation)){
                System.out.println(enlisted);
            }
        }
    }

    public void displayItems(String currentLocation){
        for(Item i: items){
            if(i.getLocation().equals(currentLocation)){
                System.out.println(i);
            }
        }
    }
}
