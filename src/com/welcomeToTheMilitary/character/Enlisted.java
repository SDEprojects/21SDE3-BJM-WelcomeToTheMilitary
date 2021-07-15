package com.welcomeToTheMilitary.character;

public class Enlisted{
    private String rank;
    private String name;
    private String attribute;
// Constructor
    public Enlisted(String _name, String _attribute, String _rank)
    {

        setName(_name);
        this.attribute = _attribute;
        this.rank = _rank;
    }

    public String getRank() {
        return this.rank;
    }

    public String getAttribute(){
        return this.attribute;}

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Enlisted{" +
                "name ='" + getName() +'\'' + "attribute = '" + attribute + '\'' ;
    }
}
