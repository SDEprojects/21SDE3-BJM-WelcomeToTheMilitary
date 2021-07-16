package com.welcomeToTheMilitary;

import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.Rank;
import com.welcomeToTheMilitary.character.ServiceMember;

public class Main {

    public static void main(String[] args) {
        System.out.println("A wild Warrior appears");
        ServiceMember celeste = new ServiceMember("Celeste", "Robust Center", "Fort Sill");
        System.out.println("Your Warrior's name is " + celeste.getName() + " and your special power is " + celeste.getSpecial());
    Enlisted mike = new Enlisted("mike", "mike", Rank.E9);
        System.out.println(mike.getRank());
    }

}
