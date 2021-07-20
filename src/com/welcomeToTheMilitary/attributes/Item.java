package com.welcomeToTheMilitary.attributes;

public class Item {
    private String name;
    private int amount;
    private String type;
    private String description = null;
    private int value;
    private String map;
    private String location;

    public Item(String _name, String _description, String _type, int _value){
        this.name = _name;
        this.description = _description;

        this.type = _type;
        this.value = _value;
    }


    public Item(String _name, String _description, String _type, int _value, String _map, String _location){
        this.name = _name;
        this.description = _description;

        this.type = _type;
        this.value = _value;
        this.map = _map;
        this.location = _location;
    }



    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Item(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue() {
        return value;
    }

    // getters and setters
    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    // toString method for print
    @Override
    public String toString() {
        return "\nItem name: " + this.getName() + '\n' +
                "Item description: " + this.getDescription() +
                "\ntype: " + type +
                "\nvalue: " + value + "\n"
        ;
    }
}
