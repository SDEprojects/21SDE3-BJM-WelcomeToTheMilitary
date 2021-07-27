package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.BaseMap;

import com.welcomeToTheMilitary.character.Rank;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import com.welcomeToTheMilitary.minigame.MinigameFactory;
import com.welcomeToTheMilitary.minigame.PTGame;
import com.welcomeToTheMilitary.minigame.iMinigame;
import com.welcomeToTheMilitary.textparser.ParseResponse;
import com.welcomeToTheMilitary.textparser.TextParser;
import com.welcomeToTheMilitary.tutorial.NewWelcome;
import com.welcomeToTheMilitary.tutorial.Welcome;
import com.welcomeToTheMilitary.gui.MainDisplay;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.util.*;

public class GameController implements java.io.Serializable {

    private static ParseResponse response = null;
    private static TextParser parser = null;
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> spellList = new ArrayList<>();
    private static MinigameFactory gameFactory = new MinigameFactory();
    private static iMinigame minigame = null;
    private static NewWelcome newWelcome;
    Welcome sepWelcome = new Welcome();



    public GameController() throws IOException, ParseException {
    }

    public void mainGame(String name, String special) throws IOException, ParseException, InterruptedException {




        BaseMap fortSill = new BaseMap("Fort Sill", "Some post");
        BaseMap fortBliss = new BaseMap("Fort Bliss", "So close to Mexico");


            BaseMap currentMap = fortSill;

            //Start the player with data gathered from gui
            ServiceMember usrSM = new ServiceMember(name, special, "Fort Sill");

            parser = new TextParser();

            // below this line while loop
            String userAction = "";
            int counter = 0;
            while (!userAction.equals("exit") && !userAction.equals("quit") && !userAction.equals("save")) {
                // condition that checks if the player's rank is E-6 then it invoke the challenge against the boss
                if (usrSM.getRank().equals(Rank.E6) && (usrSM.getPostName().equals("Fort Sill"))) {
                    MainDisplay.setMainTextArea("Reached E-6..." + "\n" +
                            "Final Challenge!"
                    );
                    System.out.println("Reached E-6...");
                    System.out.println("Final Challenge!");
                    minigame = gameFactory.playGame("boss game");
                    boolean isWon = minigame.play(usrSM);
                    // if player won
                    if (isWon) {
                        Welcome.separatorTitle();
                        MainDisplay.setMainTextArea("Your journey in Fort Sill is over soldier..");
                        System.out.println("Your journey in Fort Sill is over soldier..");
                        usrSM.setPostName("Fort Bliss");
                        currentMap = fortBliss;
                        usrSM.setLocation(currentMap.getName());
                        Welcome.separatorTitle();
                        counter = 9000000;
//                    break;
                    } else {
                        // lost in fort sill
                        Welcome.separatorTitle();

                        MainDisplay.setMainTextArea("You challenged your sergeant and lost" + "\n" + "Game Over!");

                        Welcome.separatorTitle();
                        System.exit(0);
                    }
                } else if (usrSM.getRank().equals(Rank.E9) && (usrSM.getPostName().equals("Fort Bliss"))) {
                    System.out.println("Reached E-9...");
                    System.out.println("Final Challenge!");
                    MainDisplay.setMainTextArea("Reached E-9..." + "\n" +
                            "Final Challenge!"
                    );
                    minigame = gameFactory.playGame("boss game");
                    boolean isWon = minigame.play(usrSM);
                    // if player won
                    if (isWon) {
                        Welcome.separatorTitle();
                        System.out.println("Your journey in Fort Bliss is over soldier..");
                        System.out.println("You completed the game\nYou won");
                        MainDisplay.setMainTextArea("Your journey in Fort Bliss is over soldier.." + "\n" +
                                "You completed the game! Here's your DD214."
                        );
                        Welcome.separatorTitle();
                        System.exit(0);
                    } else {
                        // lost in fort sill
                        Welcome.separatorTitle();
                        System.out.println("You challenge your sergeant major you lose\nKick out");
                        System.out.println("Game over");
                        MainDisplay.setMainTextArea("You challenged your sergeant major and lost" + "\n" + "Game Over!");
                        Welcome.separatorTitle();
                        System.exit(0);
                    }
                }
                if (counter == 0) {
                    Welcome.separatorTitle();
                    System.out.println("Welcome to Fort Sill. Your Drill Instructor dropped you off at the gate.");
                    MainDisplay.setMainTextArea("Welcome to Fort Sill. Your Drill Instructor dropped you off at the gate."+ "\n"+ "\n"  +
                            HelpmeHelper.printHelpRequestDataFromJSON(usrSM));

                    Welcome.separatorTitle();
                    counter = 1;
                } else if (counter == 9000000) {
                    Welcome.separatorTitle();
                    System.out.println("You beat the boss. You are now PCS'ed to Fort Bliss.");
                    MainDisplay.setMainTextArea("You beat the boss. You are now PCS'ed to Fort Bliss.");
                    Welcome.separatorTitle();
                }
                //display persistent information for players to track
                MainDisplay.setStatsAreaText(Display.status(usrSM));
                MainDisplay.setMapAreaText(Display.showController(usrSM, currentMap));

                //System.out.println("Enter your action [format= verb + noun] for help type (help me)\n" + "-".repeat(50));
                //userAction = input.nextLine();


                userAction = MainDisplay.getUserAction();

                response = parser.receiveAction(userAction, usrSM.getPostName());
                if (!(response.getVerb().equals("")) || !(response.getNoun().equals(""))) {
//                try {
                    switch (response.getVerb().trim()) {
                        case "go":
                            Display.enteringBuildingController(response.getNoun(), usrSM, currentMap);
                            break;
                        case "show":
                            Display.showController(response.getNoun(), usrSM, currentMap);
                            break;
                        case "talk":
                            Interactions.interactWithNPC(response.getNoun(), usrSM, currentMap);
                        case "battle":
                            Interactions.interactWithNPC(response.getNoun(), usrSM, currentMap);
                            break;
                        case "help":
                            MainDisplay.setMainTextArea(HelpmeHelper.printHelpRequestDataFromJSON(usrSM));
                            break;
                        case "request":
                            applyToPcs();
                            break;
                        case "use":
                            usrSM.useItem(response.getNoun());
                            break;
                        case "do":
                            if (response.getNoun().equals("pt")) {
                                PTGame game = new PTGame();
                                game.doPt(usrSM);
                            }
                            break;
                        case "jun":
                            System.out.println("Good job");
                            System.out.println("You WON");
                            System.exit(0);
//                        case "save":
//                            System.out.println("Saving game...");
//                            SaveAndLoad.saveGame();
//                            break;
                        default:
                            System.out.println("Verb " + response.getVerb());
                            System.out.println("Noun: " + response.getNoun());
                            break;
                    }
                    MainDisplay.setUserAction(""); // Resets userAction
//                } catch (Exception e) {
//                    System.out.println("Invalid action: type 'help me' to get info");
//                    gui.setMainTextArea("Invalid action: type 'help me' to get info");
//
//                    e.printStackTrace();
//                } // end of try-catch
                }// end of try-catch
                //counter++;
            } // end of if statement

    }

        // private method to get possible buildings for pcs
        private static void applyToPcs () throws IOException, ParseException, InterruptedException {

            System.out.println("Please type the post you would like to move to: ");
            System.out.println(JsonReader.getLocations());
            MainDisplay.setMainTextArea("Please type the post you would like to move to: " + "\n" +
                    JsonReader.getLocations());
            Scanner pcsInput = new Scanner(System.in);
            pcsInput.next();
            Thread.sleep(800);
            System.out.println("Sorry to inform you but your application has been denied");
            MainDisplay.setMainTextArea("Sorry to inform you but your application has been denied");
        }

}