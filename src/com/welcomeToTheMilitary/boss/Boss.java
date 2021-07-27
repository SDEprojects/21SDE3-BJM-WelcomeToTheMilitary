package com.welcomeToTheMilitary.boss;

import com.welcomeToTheMilitary.gui.MainDisplay;

import java.util.Random;

public class Boss {
    //Instance Fields
    private String rank;
    private String name;
    private int strength;
    private int vitality;
    private int attackMax;
    private int attackMin;

    //Constructor

    public Boss(String rank, String name, int strength, int vitality, int attackMax, int attackMin) {
        this.rank = rank;
        this.name = name;
        setStrength(strength);
        setVitality(vitality);
        setAttackMax(attackMax);
        setAttackMin(attackMin);
    }

    //Methods
    public int attack() throws InterruptedException {
        Random damage = new Random();
        int damageBase = damage.nextInt((attackMax - attackMin) +1) +attackMin;

        int attackStrength = getStrength()/damageBase;
        Math.round(attackStrength);
        System.out.println(getRank() +" "+ getName() + " attacked you for " + attackStrength + " points of damage");
        MainDisplay.setMainTextArea(getRank() +" "+ getName() + " attacked you for " + attackStrength + " points of damage");
        Thread.sleep(2000);

        return attackStrength;
    }


    //Getters & Setters


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
        if (strength >= 1) {
            this.strength = strength;
        }
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        if (vitality >= 1) {
            this.vitality = vitality;
        }

    }

    public void subtractVitality(int vitality) {
        if (vitality >= 1) {
            this.vitality -= vitality;
        }
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        if (attackMin>0) {
            this.attackMin = attackMin;
        }
    }

    @Override
    public String toString() {
        return "Final Boss: {  Rank: " + rank +
                ", Name: " + name +
                ", Strength: " + strength +
                ", Vitality: " + vitality +
                '}';
    }
}
