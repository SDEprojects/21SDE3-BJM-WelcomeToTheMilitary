package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.Fort_Bliss_Map;
import com.welcomeToTheMilitary.bases.Fort_Sill_Map;
import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.minigame.MinigameFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Interactions {
    public static void interactWithNPC(String noun, ServiceMember usrSM, Fort_Sill_Map fortSill, Fort_Bliss_Map fortBliss) {
        MinigameFactory gameFactory = new MinigameFactory();
        if (noun == null || noun.length() == 0) {
            System.out.println("Invalid soldier");
            return;
        }
        String playerCurrentLocation = usrSM.getLocation();
        String playerCurrentPost = usrSM.getPostName();
        // check the soldier's existence
        ArrayList<Enlisted> existingSolider = null;
        ArrayList<Enlisted> existingSeniorSolider = null;
        if (playerCurrentPost.equals("Fort Sill")) {
            existingSolider = fortSill.getSolider(playerCurrentLocation);
        } else {
            existingSeniorSolider = fortBliss.getSolider(playerCurrentLocation);
        }
        if (playerCurrentPost.equals("Fort Sill")) {
            if (existingSolider == null || existingSolider.size() == 0) {
                System.out.println("There is no one in the area... T_T");
                return;
            }
        }
        if (playerCurrentPost.equals("Fort Bliss")) {
            if (existingSeniorSolider == null || existingSeniorSolider.size() == 0) {
                System.out.println("There is no one in the area... T_T");
                return;
            }
        }
        String targetSoldierName = noun.substring(0, 1).toUpperCase() + noun.substring(1);
        Enlisted soldier = null;
        Enlisted senior = null;
        if (existingSolider != null) {
            for (Enlisted eachSolider : existingSolider) {
                if (eachSolider.getName().equals(targetSoldierName)) {
                    soldier = eachSolider;
                    break;
                }
            }
        } else if (existingSeniorSolider != null){
            for (Enlisted eachSolider : existingSeniorSolider) {
                if (eachSolider.getName().equals(targetSoldierName)) {
                    senior = eachSolider;
                    break;
                }
            }
        }


        // founded case
        if (soldier != null) {
            System.out.println("Targeting:" + noun);
            System.out.println("You finally saw " + soldier.getName() + "'s rank!\nIt is " + soldier.getRank());
            // game start
            // boolean isWin = rockPaperScissors.play();

            gameFactory.playGame();

            boolean isWin = gameFactory.playGame().play(); //access's miniGame and returns true or false from MiniGame if Won.

            System.out.println("Win or lose: " + isWin);
            if (isWin) {
                if (usrSM.getPostName().equals("Fort Sill")){
                    // TODO: initialize item using JSON is done, work on store it in the inventory
                    usrSM.storeItemInVentory(fortSill.showItemInTheFacilityTest(usrSM.getLocation()));
                    boolean isWorthRank = PromoteHelper.isRankWorthItFortSill(usrSM, soldier);
                    if (isWorthRank) {
                        usrSM.setRank(soldier.getRank());
                        System.out.println("Congrats you won the interaction.\n" +
                                "Obtained item: " + fortSill.showItemInTheFacilityTest(usrSM.getLocation()) + "\n" +
                                "Player's current rank: " + usrSM.getRank() + "\nObtained item can be used during final" +
                                "combat during the Boss stage.");
                    }
                } else if (usrSM.getPostName().equals("Fort Bliss")){
//                    need to create items
                    System.out.println("No items available in Fort Bliss currently.");
                }
                else {
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
                    if (healAnswer.equals("yes") || healAnswer.equals("y")){
                        usrSM.setHealth(15, true);
                        System.out.println("You used the healing potion and healed by 15");
                        usrSM.setHealPotion();
                        System.out.println("Your current health is " + usrSM.getHealth() + "\nYou now have " + usrSM.getHealPotion() + " heal potion");

                    }
                } else {
                    System.out.println("You have no health potion");
                }
            }
            return;
        } else if (senior != null){
            System.out.println("Targeting:" + noun);
            System.out.println("You finally saw " + senior.getName() + "'s rank!\nIt is " + senior.getRank());

            gameFactory.playGame();
            boolean isWin = gameFactory.playGame().play(); //returns true/false if game is won

            System.out.println("Win or lose: " + isWin);
            if (isWin) {
                if (usrSM.getPostName().equals("Fort Bliss")){
                    usrSM.storeItemInVentory(fortBliss.showItemInTheFacilityTest(usrSM.getLocation()));
                    boolean isWorthRank = PromoteHelper.isRankWorthItForBliss(usrSM, senior);
                    if (isWorthRank) {
                        usrSM.setRank(senior.getRank());
                        System.out.println("Congrats you won the interaction.\n" +
                                "Obtained item: " + fortBliss.showItemInTheFacilityTest(usrSM.getLocation()) + "\n" +
                                "Player's current rank: " + usrSM.getRank() + "\nObtained item can be used during final" +
                                "combat during the Boss stage.");
                    }
                } else if (usrSM.getPostName().equals("Fort Bliss")){
//                    need to create items
                    System.out.println("No items available in Fort Bliss currently.");
                }
                else {
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
                    if (healAnswer.equals("yes") || healAnswer.equals("y")){
                        usrSM.setHealth(15, true);
                        System.out.println("You used the healing potion and healed by 15");
                        usrSM.setHealPotion();
                        System.out.println("Your current health is " + usrSM.getHealth() + "\nYou now have " + usrSM.getHealPotion() + " heal potion");

                    }
                } else {
                    System.out.println("You have no health potion");
                }
            }
            return;
        }
        System.out.println("Cannot find the soldier you are looking for");
        return;
    }
}
