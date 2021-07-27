package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.character.Enlisted;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.MainDisplay;

public class PromoteHelper {


    public static boolean checkRank(ServiceMember player, Enlisted soldier) {
        return player.getRank().getValue() < soldier.getRank().getValue();
    }

    public static void promote(ServiceMember player, Enlisted soldier) throws InterruptedException {
        player.setRank(soldier.getRank());
        System.out.println("Congratulations! You've been promoted to + " + player.getRank().getAbbreviation());
        MainDisplay.setMainTextArea("Congratulations! You've been promoted to + " + player.getRank().getAbbreviation());
        Thread.sleep(200);
    }
}
