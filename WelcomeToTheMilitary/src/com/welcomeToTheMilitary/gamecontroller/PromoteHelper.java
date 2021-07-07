package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.character.LowerEnlist;
import com.welcomeToTheMilitary.character.ServiceMember;

public class PromoteHelper {
    // method to cover lower enlist and the player
    public static boolean isRankWorthIt(ServiceMember player, LowerEnlist soldier) {
        // if the rank is fuzzy then just promote
        if (player.getRank().equals("fuzzy")) {
            return true;
        }
        String playerSplitRank[] = player.getRank().split("-");
        String soldierSplitRank[] = soldier.getRank().split("-");
        try {
            int playerRankNumber = Integer.parseInt(playerSplitRank[1]);
            int soldierRankNumber = Integer.parseInt(soldierSplitRank[1]);
            if (soldierRankNumber > playerRankNumber) {
                return true;
            }
            return false;
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Exception: Converting rank into integer");
            numberFormatException.printStackTrace();
            return false;
        }
    }
}
