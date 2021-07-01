package com.dependapot.character;

import java.util.ArrayList;

public class WarrantOfficer {
    private String rank;
    private String name;
    private String attribute;

    public WarrantOfficer(String _rank, String _name, String _attribute) {
        this.rank = _rank;
        this.name = _name;
        this.attribute = _attribute;
    }
    public String getRank() {
        return this.rank;
    }

    public String getAttribute(){
        return this.attribute;}

    public String getName(){
        return this.name;
    }


}
