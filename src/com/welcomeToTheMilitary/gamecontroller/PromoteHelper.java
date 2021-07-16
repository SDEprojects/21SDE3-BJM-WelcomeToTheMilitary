package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.character.Enlisted;

import com.welcomeToTheMilitary.character.ServiceMember;

public class PromoteHelper {


    public static boolean checkRank(ServiceMember player, Enlisted soldier) {
        return player.getRank().getValue() > soldier.getRank().getValue();
    }

    public static void promote(ServiceMember player, Enlisted soldier) {
        player.setRank(soldier.getRank());
        System.out.println("Congratulations! You've been promoted to + " + player.getRank().getAbbreviation());
    }
}
