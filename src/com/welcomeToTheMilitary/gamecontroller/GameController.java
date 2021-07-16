package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.Fort_Bliss_Map;
import com.welcomeToTheMilitary.bases.Fort_Sill_Map;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.minigame.MinigameFactory;
import com.welcomeToTheMilitary.minigame.iMinigame;
import com.welcomeToTheMilitary.textparser.ParseResponse;
import com.welcomeToTheMilitary.textparser.TextParser;
import com.welcomeToTheMilitary.tutorial.Welcome;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class GameController {
    private static ParseResponse response = null;
    private static TextParser parser = null;
    private static Fort_Sill_Map fortSill = new Fort_Sill_Map("Fort Sill", "Some post");
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> spellList = new ArrayList<>();
    private static Fort_Bliss_Map fortBliss = new Fort_Bliss_Map("Fort Bliss", "So close to Mexico");
    //private Display fortSill = new Display(Fort_Sill_Map)
    // minigame
    //private static FinalBoss fortSillFinalBoss =new FinalBoss("SFC", "Daniels", 1, 1);
    private static MinigameFactory gameFactory = new MinigameFactory();
    private static iMinigame minigame = null;

    public static void main(String[] args) throws InterruptedException {


        ServiceMember usrSM = Welcome.intro(spellList);
        parser = new TextParser();

        // below this line while loop
        String userAction = "";
        int counter = 0;
        while (!userAction.equals("exit") && !userAction.equals("quit")) {
            // condition that checks if the player's rank is E-6 then it invoke the challenge against the boss
            if (usrSM.getRank().equals("E-6") && (usrSM.getPostName().equals("Fort Sill"))) {
                System.out.println("Reached E-6...");
                System.out.println("Final Challenge!");
                minigame = gameFactory.playGame("boss game");
                boolean isWon = minigame.play(usrSM);
                // if player won
                if (isWon) {
                    System.out.println("Your journey in Fort Sill is over soldier..");
                    usrSM.setPostName("Fort Bliss");
                    counter = 9000000;
//                    break;
                } else {
                    // lost in fort sill
                    System.out.println("You challenge your sergeant you lose\nKick out");
                    System.out.println("Game over");
                    System.exit(0);
                }
            } else if (usrSM.getRank().equals("E-9") && (usrSM.getPostName().equals("Fort Bliss"))) {
                System.out.println("Reached E-9...");
                System.out.println("Final Challenge!");
                minigame = gameFactory.playGame("boss game");
                boolean isWon = minigame.play(usrSM);
                // if player won
                if (isWon) {
                    System.out.println("Your journey in Fort Bliss is over soldier..");
                    System.out.println("You completed the game\nYou won");
                    System.exit(0);
                } else {
                    // lost in fort sill
                    System.out.println("You challenge your sergeant major you lose\nKick out");
                    System.out.println("Game over");
                    System.exit(0);
                }
            }
            if (counter == 0) {
                System.out.println("Welcome to Fort Sill. Your Drill Instructor dropped you off at the gate.");
            } else if (counter == 9000000) {
                System.out.println("You beat the boss. You are now PCS'ed to Fort Bliss.");
            }
            System.out.println("Enter your action [format= verb + noun] for help type (help me)");
            userAction = input.nextLine();
            response = parser.receiveAction(userAction, usrSM.getPostName());
            if (!(response.getVerb().equals("")) || !(response.getNoun().equals(""))) {
                try {
                    switch (response.getVerb().trim()) {
                        case "go":
                            Display.enteringBuildingController(response.getNoun(), usrSM, fortSill, fortBliss);
                            break;
                        case "show":
                            Display.showController(response.getNoun(), usrSM, fortSill, fortBliss);
                            break;
                        case "talk":
                            Interactions.interactWithNPC(response.getNoun(), usrSM, fortSill, fortBliss);
                            break;
                        case "help":
                            HelpmeHelper.interactHelpRequest(response.getNoun(), usrSM);
                            break;
                        case "request":
                            // method to retrieve all possible post
                            String pcsSelect = getPossibleBuildingsForPCS(response.getNoun(), usrSM);
                            usrSM.setPcsRequest(pcsSelect);
                            System.out.println("You submitted the pcs form to " + pcsSelect);


                            break;
                        case "jun":
                            System.out.println("Good job");
                            System.out.println("You WON");
                            System.exit(0);
                        default:
                            System.out.println("Verb " + response.getVerb());
                            System.out.println("Noun: " + response.getNoun());
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid action: type 'help me' to get info");
                    e.printStackTrace();
                } // end of try-catch
            }// end of try-catch
            counter++;
        } // end of if statement
    } // end of while loop

    // private method to get possible buildings for pcs
    private static String getPossibleBuildingsForPCS(String noun, ServiceMember usrSM) {

        //replace with "DENIED"
        InputStream locationJSONFile = GameController.class.getResourceAsStream("/locations.json");
        JSONParser parserForLocation = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(locationJSONFile))) {
            JSONObject locationObj = (JSONObject) parserForLocation.parse(reader);
            String[] keysArrItem = Arrays.copyOf(locationObj.keySet().toArray(), locationObj.keySet().toArray().length, String[].class);
            ArrayList<String> pcsLocationList = new ArrayList<>();
            Scanner pcsInput = new Scanner(System.in);
            int whileInt = 0;
            while (whileInt == 0) {
                System.out.println("Please type in the number associated with the post you would like to move to: ");
                int x = 1;

                for (String key : keysArrItem) {
                    System.out.println(x + ". " + key);
                    x++;
                }

                try{
                    int pcsSelection = pcsInput.nextInt();

                    if(pcsSelection > 0 && pcsSelection < keysArrItem.length +1) {
                        pcsSelection--;
                        return keysArrItem[pcsSelection];
                    }
                }catch (InputMismatchException ignored){

                }
            }

        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
        return "";
    }




}