package com.welcomeToTheMilitary.character;

public class LowerEnlist {
    private String rank;
    private String name;
    private String attribute;
// Constructor
    public LowerEnlist(String _name, String _attribute, String _rank)
    {
        this.name = _name;
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

    @Override
    public String toString() {
        return "LowerEnlist{" +
                "name ='" + name +'\'' + "attribute = '" + attribute + '\'' ;
    }
}
