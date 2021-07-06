package com.dependapot.attributes;

public class Item {
public String name;
public Attributes attr;
public int amount;
boolean stackable = false;
public String type;

public Item(String _name){
    this.name = _name;
    this.attr = new Attributes(0, 0);
    this.type = "item";
}

    public String getName(){
    return name;
    }

    public String toString(){
    return name;
    }

    public Attributes getAt(){
    return attr;
    }

    public String getType(){
    return type;
    }

    public int getAmount(){
        return amount;
    }

    public boolean addToStack(){

        if(stackable){
            amount++;
            return true;
        }
        return false;
    }


}
