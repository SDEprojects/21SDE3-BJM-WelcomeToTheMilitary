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

    private ArrayList<WarrantOfficer> warrantsInBliss(){
        ArrayList<WarrantOfficer> fortSill = new ArrayList<>();
        WarrantOfficer WO1 = new WarrantOfficer("WO1", "Chet", "I just traded my 28% APR Camaro for a GeoMetro");
        WarrantOfficer WO2 = new WarrantOfficer("WO2", "David", "I don't wash my coffee cup. I also work with computers. Do you DND?");
        WarrantOfficer WO3 = new WarrantOfficer("WO3", "Michael", "I just left my wife because I want to focus on my career. I'm a Warrant Officer, you know. I can do this forever.");
        WarrantOfficer WO4 = new WarrantOfficer("WO4", "Phillip", "What year is it? Come with me if you want travel the world.");
        WarrantOfficer WO5 = new WarrantOfficer("WO5", "Garcia", "Who am I, you ask? I don't exist. I play golf during work hours.");

        fortSill.add(WO1);
        fortSill.add(WO2);
        fortSill.add(WO3);
        fortSill.add(WO4);
        fortSill.add(WO5);
        return fortSill;
    }
}
