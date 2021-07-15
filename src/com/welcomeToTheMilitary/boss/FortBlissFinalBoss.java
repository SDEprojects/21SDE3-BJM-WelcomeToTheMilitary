package com.welcomeToTheMilitary.boss;

import java.util.Random;

public class FortBlissFinalBoss {
    public FortBlissFinalBoss(String rank, String name, int _strength, int _vitality) {

    }

    @Override
    public int attack(){
        int max = 70;
        int min = 30;
        Random damage = new Random();
        int x = damage.nextInt((max - min) +1) +min;
        System.out.println(x);
        int att = getStrength()/x;
        Math.round(att);
        System.out.println(getRank() +" "+ getName() + " attacked you for " + att + " points of damage");
        return att;
    }

    @Override
    public String toString() {
        return "FortBliss Final Boss: {  Rank: " + getRank() +
                ", Name: " + getName() +
                ", Strength: " + getStrength() +
                ", Vitality: " + getVitality() +
                '}';
    }
}
