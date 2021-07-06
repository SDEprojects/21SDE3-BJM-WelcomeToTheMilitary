package com.dependapot.gamecontroller;

import com.dependapot.bases.Fort_Sill_Map;
import com.dependapot.character.Dependa;
import com.dependapot.character.LowerEnlist;
import com.dependapot.minigame.MinigameFactory;
import com.dependapot.minigame.RPC;
import com.dependapot.minigame.iMinigame;
import com.dependapot.textparser.ParseResponse;
import com.dependapot.textparser.TextParser;
import com.dependapot.tutorial.Welcome;

import java.util.*;

public class GameController {

    private static ParseResponse response = null;
    private static TextParser parser = null;
    private static Fort_Sill_Map fortSill = new Fort_Sill_Map("Fort Sill", "Some post");
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> spellList = new ArrayList<>();

    // minigame
    private static MinigameFactory gameFactory = new MinigameFactory();
    private static iMinigame minigame = null;
    private static iMinigame rockPaperScissors = new RPC();

    public static void main(String[] args) {
//        spellList.add("Bumper Sticker");
//        spellList.add("LuLuRoe Business Card");

        Dependa usrDep = Welcome.intro(spellList);
        parser = new TextParser();

        // below this line while loop
        String userAction = "";
        int counter = 0;
        while (!userAction.equals("exit") && !userAction.equals("quit")) {
            if(counter ==0){
                System.out.println("Welcome to Fort Sill, you entered through the trunk of a homegirl's car.");
            }
            System.out.println("Enter your action [format= verb + noun] for help type (help me)");
            userAction = input.nextLine();
            response = parser.receiveAction(userAction, fortSill.getName());
            if (!(response.getVerb().equals("")) || !(response.getNoun().equals(""))) {
                System.out.println("Verb: " + response.getVerb());
                try {
                    switch (response.getVerb().trim()) {
                        case "go":
                        case "move":
                        case "drive":
                        case "walk":
                        case "run":
                            GameController.enteringBuildingController(response.getNoun(), usrDep);
                            break;
                        case "display":
                        case "show":
                            GameController.displayBuildings(response.getNoun(), usrDep);
                            break;
                        case "talk":
                        case "approach":
                        case "interact":
                            GameController.interactWithNPC(response.getNoun(), usrDep);
                            break;
                        case "help":
//                        case "quit":
//                        case "exit":
                            interactHelpRequest(response.getNoun(), usrDep);
                            break;
                        default:
                            System.out.println("Verb " + response.getVerb());
                            System.out.println("Noun: " + response.getNoun());
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid action: type 'help me' to get info");
                } // end of try-catch
            }// end of try-catch
            counter++;
        } // end of if statement
    } // end of while loop

    // generate random game
    private static String prepareRandomGame() {
        ArrayList<String> gameList = new ArrayList<>();
        gameList.add("rock paper scissors");
        gameList.add("memorization game");
        final int min = 0;
        final int max = gameList.size();
        int randomIndex = (int) (Math.random() * (max - min));
        return gameList.get(randomIndex);
    }

    private static void interactWithNPC(String noun, Dependa usrDep) {
        if (noun == null || noun.length() == 0) {
            System.out.println("Invalid soldier");
            return;
        }
        String playerCurrentLocation = usrDep.getLocation();
        // check the soldier's existence
        ArrayList<LowerEnlist> existingSolider = fortSill.getSolider(playerCurrentLocation);
        if (existingSolider == null || existingSolider.size() == 0) {
            System.out.println("There is no one in the area... T_T");
            return;
        }
        String targetSoldierName = noun.substring(0, 1).toUpperCase() + noun.substring(1);
        LowerEnlist solider = null;
        for (LowerEnlist eachSolider : existingSolider) {
            if (eachSolider.getName().equals(targetSoldierName)) {
                solider = eachSolider;
                break;
            }
        }
        // founded case
        if (solider != null) {
            System.out.println("Targeting:" + noun);
            System.out.println("You finally saw " + solider.getName() + "'s rank!\nIt is " + solider.getRank());
            // game start
            // boolean isWin = rockPaperScissors.play();
            minigame = gameFactory.playGame(prepareRandomGame());
            boolean isWin = minigame.play();
            if (isWin) {
                // placeholder to check it gets the correct items based on the facility
                System.out.println(usrDep.getLocation());
                System.out.println(fortSill.getItemFromFacility(usrDep.getLocation()));
                // end of the placeholder
                System.out.println("Congrats you won the interaction.");
            } else {
                System.out.println("You have lost. Search for a new scrub!!!");
            }
            return;
        }
        System.out.println("Cannot find the soldier you are looking for");
        return;
    }

    private static void interactHelpRequest(String noun, Dependa usrDep) {
        switch (noun) {
//            case"exit":
//            case "quit":
//                System.out.println("Thanks for playing");
//                System.exit(0);
            default:
                System.out.println("=".repeat(5) + " Movement " + "=".repeat(5));
                System.out.println("Supported movement verb: go | move | drive | walk | run");
                System.out.println("Supported movement noun: dfac | barracks | church | px | market | gym");
                System.out.println("Example: go dfac | move dfac");
                System.out.println("=".repeat(5) + " Interact to Soldier " + "=".repeat(5));
                System.out.println("Supported interact verb: talk | approach | interact");
                System.out.println(
                        "Supported interact noun: brad | jeremy | rogers | shad | arturo | john | brandon |" +
                        "laginus | soko | david | stephen");
                System.out.println("Example: talk brad | approach jeremy");
                System.out.println("=".repeat(5) + " Display possible building in current post " + "=".repeat(5));
                System.out.println("Supported display verb: show | display");
                System.out.println("Supported display noun: map | location | buildings | building");
                System.out.println("Example: | display map | show map");
        }
    }

    private static void displayBuildings(String noun, Dependa usrDep) {
        switch (noun) {
//            add buildings
            default:
                System.out.println("These are the possible location you can go!!");
                System.out.println(fortSill.getBuildings());
                break;
        }
    }

    private static void enteringBuildingController(String noun, Dependa usrDep) {
        switch (noun) {
            case "dfac":
            case "px":
            case "church":
            case "gym":
            case "barracks":
            case "market":
                System.out.println("EnteringBuildingController: " + noun);
                fortSill.enterToBuilding(noun);
//                setDependaLocation(noun, usrDep);
                usrDep.setLocation(noun);
                System.out.println("Curent " + usrDep.getName() + "'s location: " + usrDep.getLocation());
                break;
            default:
                System.out.println("These are the possible location you can go!!");
                System.out.println(fortSill.getBuildings());
                break;
        }
    }
}