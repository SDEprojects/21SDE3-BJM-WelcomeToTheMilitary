package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.BaseMap;

import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.minigame.MinigameFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Interactions {
    public static void interactWithNPC(String noun, ServiceMember usrSM, BaseMap currentMap) {
        MinigameFactory gameFactory = new MinigameFactory();
        if (noun == null || noun.length() == 0) {
            System.out.println("Invalid soldier");
            return;
        }
        String playerCurrentLocation = usrSM.getLocation();

        // check the soldier's existence
        ArrayList<Enlisted> existingSolider = currentMap.getSoldiers(playerCurrentLocation);

        if (existingSolider == null || existingSolider.size() == 0) {
            System.out.println("There is no one in the area... T_T");
            return;
        }


        String targetSoldierName = noun.substring(0, 1).toUpperCase() + noun.substring(1);
        Enlisted soldier = null;
        if (existingSolider != null) {
            for (Enlisted eachSoldier : existingSolider) {
                if (eachSoldier.getName().equals(targetSoldierName)) {
                    soldier = eachSoldier;
                    break;
                }
            }


            // founded case
            if (soldier != null) {
                System.out.println("Targeting:" + noun);
                System.out.println("You finally saw " + soldier.getName() + "'s rank!\nIt is " + soldier.getRank());
                // game start

                //commented out for testing purposes
                //only accepts fight if the opposing players rank is at most higher than 2
//                if(soldier.getRank().ordinal() - usrSM.getRank().ordinal() > 2){
//                    System.out.println("Their rank is much higher. Challenge someone who's closer to your rank");
//                    return;
//                }

                gameFactory.playGame();

                boolean isWin = gameFactory.playGame().play(); //access's miniGame and returns true or false from MiniGame if Won.

                System.out.println("Win or lose: " + isWin);
                if (isWin) {
                    if (usrSM.getPostName().equals("Fort Sill") || usrSM.getPostName().equals("Fort Bliss")) {

                        usrSM.storeItemInVentory(currentMap.getCurrentItem(usrSM.getLocation())); //removes item from map and adds to user inventory
                        boolean isWorthRank = PromoteHelper.checkRank(usrSM, soldier);
                        if (isWorthRank) {
                            PromoteHelper.promote(usrSM, soldier);
                            System.out.println("Congrats you won the interaction.\n");
                        } else {
                            System.out.println("Congrats you won the interaction.");
                            System.out.println("You decided not to take their rank\n" + "It is lower than yours yuck!");
                        }
                    } else {
                        System.out.println("You have lost. You maintain your rank but lost your dignity!!!");
                        System.out.println("You lost 5 hp");
                        if (usrSM.getHealth() <= 0) {
                            System.out.println("You got article 15..");
                            System.out.println("Separation package...\nBye");
                            System.exit(0);
                        }
                        usrSM.setHealth(5, false);
                        if (usrSM.getHealPotion() > 0) {
                            System.out.println("Would you like to heal?");
                            // yes or no
                            Scanner healInput = new Scanner(System.in);
                            String healAnswer = healInput.nextLine().toLowerCase();
                            while (!healAnswer.equals("yes") && !healAnswer.equals("no") && !healAnswer.equals("y")
                                    && !healAnswer.equals("n")) {
                                System.out.println("Please enter yes/y or no/n.");
                                healAnswer = healInput.nextLine().toLowerCase();
                            }
                            if (healAnswer.equals("yes") || healAnswer.equals("y")) {
                                usrSM.setHealth(15, true);
                                System.out.println("You used the healing potion and healed by 15");
                                usrSM.setHealPotion();
                                System.out.println("Your current health is " + usrSM.getHealth() + "\nYou now have " + usrSM.getHealPotion() + " heal potion");

                            }
                        } else {
                            System.out.println("You have no health potion");
                        }
                    }
                }

            }
        }
    }
}
