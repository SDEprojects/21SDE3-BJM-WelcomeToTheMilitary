package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.BaseMap;

import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.mainDisplay;
import com.welcomeToTheMilitary.minigame.MinigameFactory;
import com.welcomeToTheMilitary.textparser.ParseResponse;
import com.welcomeToTheMilitary.textparser.TextParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Interactions {

    private static ParseResponse response = null;
    private static TextParser parser = new TextParser();

    public static void interactWithNPC(String noun, ServiceMember usrSM, BaseMap currentMap) throws IOException, ParseException {
        MinigameFactory gameFactory = new MinigameFactory();
        if (noun == null || noun.length() == 0) {
            System.out.println("Invalid soldier");
            mainDisplay.setMainTextArea("Invalid Soldier");
            return;
        }
        String playerCurrentLocation = usrSM.getLocation();

        // check the soldier's existence
        ArrayList<Enlisted> existingSolider = currentMap.getSoldiers(playerCurrentLocation);

        if (existingSolider == null || existingSolider.size() == 0) {
            System.out.println("There is no one in the area... T_T");
            mainDisplay.setMainTextArea("There is no one in the area... T_T");
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

                //gives a choice to battle or talk to player
                System.out.println("Would you like to talk or battle?");
                mainDisplay.setMainTextArea("Would you like to talk or battle?");

                String userAction = mainDisplay.getUserAction();
                response = parser.receiveAction(userAction, usrSM.getPostName());

                //if player wants to talk
                if(userAction.equals("talk " + soldier.getName())){
                    JSONParser jsonParser = new JSONParser();
                    String jsonSoldiers = "jsonFiles/soldiersTest.json";
                    String soldiersContents = new String((Files.readAllBytes((Paths.get(jsonSoldiers)))));

                    JSONObject j = (JSONObject) jsonParser.parse(soldiersContents);
                    JSONObject soldiers = (JSONObject) j.get(soldier.getName());
                    String soldierLine = soldiers.get("line").toString();
                    System.out.println(soldierLine);
                    mainDisplay.setMainTextArea(soldierLine);

                    return;
                }
                else if(!userAction.equals("battle")){
                    System.out.println("not a valid choice");
                    mainDisplay.setMainTextArea("not a valid choice");
                    return;
                }

                //player won't be able to interact with the same npc if already won
                if(!soldier.isCanInteract()){
                    System.out.println("You already won the interaction with this person");
                    mainDisplay.setMainTextArea("You already won the interaction with this person");
                    return;
                }

                //player engaging battle
                System.out.println("Targeting:" + noun);
                System.out.println("You finally saw " + soldier.getName() + "'s rank!\nIt is " + soldier.getRank());
                mainDisplay.setMainTextArea("Targeting: " + noun + "\n" +
                        "You finally saw " + soldier.getName() + "'s rank!\nIt is " + soldier.getRank()
                );

                // game start

                //only accepts fight if the opposing players rank is at most higher than 2
                if(soldier.getRank().ordinal() - usrSM.getRank().ordinal() > 2){
                    System.out.println("Their rank is much higher. Challenge someone who's closer to your rank");
                    mainDisplay.setMainTextArea("Their rank is much higher. Challenge someone who's closer to your rank");
                    return;
                }

                gameFactory.playGame();

                boolean isWin = gameFactory.playGame().play(); //access's miniGame and returns true or false from MiniGame if Won.

                //loses 5 health when player loses in minigame
                if(!isWin){
                    usrSM.setHealth(usrSM.getHealth() - 5);
                }

                System.out.println("Win or lose: " + isWin);
                mainDisplay.setMainTextArea("Win or lose: " + isWin);
                if (isWin) {
                    if (usrSM.getPostName().equals("Fort Sill") || usrSM.getPostName().equals("Fort Bliss")) {

                        //setting the interaction to false so player can't interact with the same person
                        soldier.setCanInteract(false);

                        usrSM.storeItemInVentory(currentMap.getCurrentItem(usrSM.getLocation())); //removes item from map and adds to user inventory
                        boolean isWorthRank = PromoteHelper.checkRank(usrSM, soldier);
                        if (isWorthRank) {
                            PromoteHelper.promote(usrSM, soldier);
                            System.out.println("Congrats you won the interaction.\n");
                            mainDisplay.setMainTextArea("Congrats you won the interaction.\n");
                        } else {
                            System.out.println("Congrats you won the interaction.");
                            System.out.println("You decided not to take their rank\n" + "It is lower than yours yuck!");
                            mainDisplay.setMainTextArea("Congrats you won the interaction." +
                                    "You decided not to take their rank\n.  It is lower than yours yuck!");
                        }
                    } else {
                        System.out.println("You have lost. You maintain your rank but lost your dignity!!!");
                        System.out.println("You lost 5 hp");
                        mainDisplay.setMainTextArea("You have lost. You maintain your rank but lost your dignity!!!" +
                                "You lost 5 hp"
                                );

                        if (usrSM.getHealth() <= 0) {
                            System.out.println("You got article 15..");
                            System.out.println("Separation package...\nBye");
                            mainDisplay.setMainTextArea("You got article 15.." +
                                    "Separation package...\n Bye");
                            System.exit(0);
                        }
                        usrSM.setHealth(5, false);
                        if (usrSM.getHealPotion() > 0) {
                            System.out.println("Would you like to heal?");
                            mainDisplay.setMainTextArea("Would you like to heal?");

                            // yes or no
                            Scanner healInput = new Scanner(System.in);
                            String healAnswer = healInput.nextLine().toLowerCase();
                            while (!healAnswer.equals("yes") && !healAnswer.equals("no") && !healAnswer.equals("y")
                                    && !healAnswer.equals("n")) {
                                System.out.println("Please enter yes/y or no/n.");
                                mainDisplay.setMainTextArea("Please enter yes/y or no/n");

                                healAnswer = healInput.nextLine().toLowerCase();
                            }
                            if (healAnswer.equals("yes") || healAnswer.equals("y")) {
                                usrSM.setHealth(15, true);
                                System.out.println("You used the healing potion and healed by 15");
                                usrSM.setHealPotion();
                                mainDisplay.setMainTextArea("You used the healing potion and healed by 15" +
                                        "Your current health is " + usrSM.getHealth() + "\n You now have " + usrSM.getHealPotion() + " heal potion");
                                System.out.println("Your current health is " + usrSM.getHealth() + "\nYou now have " + usrSM.getHealPotion() + " heal potion");

                            }
                        } else {
                            System.out.println("You have no health potion");
                            mainDisplay.setMainTextArea("You have no health potion");
                        }
                    }
                }

            }
        }
    }
}
