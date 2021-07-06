package com.dependapot.character;

import com.dependapot.attributes.Weapons;

public class FinalBoss{
    private String rank;
    private String name;
    private Weapons cidWeapon = new Weapons("Baton", 10,15,6);
    private int strength;
    private int vitality;


    //public Weapons(String _name,int strength, int vitality, int level){
    //CID soldier is the final boss
    public FinalBoss (String rank, String name, int _strength, int _vitality, Weapons cidWeapon){
        this.strength = _strength;
        this.vitality = _vitality;
        this.rank = rank;
        this.name = name;
        this.cidWeapon = cidWeapon;
    }
    public static void main(String[] args) {
        FinalBoss ssg = new FinalBoss("SFC", "Daniels", 15,25,new Weapons("Fists",5,5,5));
    }
}
