package com.dependapot;

import com.dependapot.character.Dependa;

public class Main {

    public static void main(String[] args) {
        System.out.println("A wild Warrior appears");
        Dependa celeste = new Dependa("Celeste", "Robust Center", "Fort Sill");
        System.out.println("Your Warrior's name is " + celeste.getName() + " and your special power is " + celeste.getSpecial());


    }

}
