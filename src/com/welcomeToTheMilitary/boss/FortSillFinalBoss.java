package com.welcomeToTheMilitary.boss;

import java.util.Random;

public class FortSillFinalBoss{

    private String rank;
    private String name;
    private int strength;
    private int vitality;

    public FortSillFinalBoss(String rank, String name, int _strength, int _vitality ) {

    }

    public int attack(){
        int max = 5;
        int min = 2;
        Random damage = new Random();
        int x = damage.nextInt((max - min) +1) +min;
        System.out.println(x);
        int att = getStrength()/x;
        Math.round(att);
        System.out.println(getRank() +" "+ getName() + " attacked you for " + att + " points of damage");
        return att;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public String toString() {
        return "FortSill Final Boss: {  Rank: " + getRank() +
                ", Name: " + getName() +
                ", Strength: " + getStrength() +
                ", Vitality: " + getVitality() +
                '}';
    }

}
