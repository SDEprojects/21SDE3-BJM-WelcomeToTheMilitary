package com.welcomeToTheMilitary.character;

public class Enlisted{
    private Rank rank;
    private String name;
    private String attribute;
    private String location;

// Constructor
    public Enlisted(String _name, String _attribute, Rank _rank)
    {
        setName(_name);
        this.attribute = _attribute;
        this.rank = _rank;
    }

    //Getters & Setters

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
