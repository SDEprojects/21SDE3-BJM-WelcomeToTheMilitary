package com.welcomeToTheMilitary.character;

public class Enlisted{

    private String name;
    private String attribute;
    private Rank rank;
    private String location;

// Constructors

    //temporary
public Enlisted(String _name, String _attribute, Rank _rank)
{
    setName(_name);
    this.attribute = _attribute;
    this.rank = _rank;
}

//Constructor for JSON loading
    public Enlisted(String _name, String _attribute, String _rank, String location)
    {
        setName(_name);
        setAttribute(_attribute);
        setRank(_rank);
        setLocation(location);
    }

    //Getters & Setters

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    //String to rank converter for JSON loading
    public void setRank(String rank) {
        switch (rank) {
            case "Rank.E2" -> this.rank = Rank.E2;
            case "Rank.E3" -> this.rank = Rank.E3;
            case "Rank.E4" -> this.rank = Rank.E4;
            case "Rank.E5" -> this.rank = Rank.E5;
            case "Rank.E6" -> this.rank = Rank.E6;
            case "Rank.E7" -> this.rank = Rank.E7;
            case "Rank.E8" -> this.rank = Rank.E8;
            case "Rank.E9" -> this.rank = Rank.E9;
            default -> this.rank = Rank.E1;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.equals("")) {
            this.name = name;
        }

    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        if (attribute != null && !attribute.equals("")) {
            this.attribute = attribute;
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null && !location.equals("")) {
            this.location = location;
        }
    }
}
