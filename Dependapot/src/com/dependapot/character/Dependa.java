package com.dependapot.character;

public class Dependa extends Character {
     private String special = "Baking";
//     Stretch goal is to add private Bldg location pulling from Post/Fort Sill Map
     private String location;

// Dependa Constructor that pulls from Character class
    public Dependa(String _name, String _special, String _location ) {
        super(_name);
        this.special = _special;
        this.location = _location;
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
}
