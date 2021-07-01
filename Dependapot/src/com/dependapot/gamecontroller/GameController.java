package com.dependapot.gamecontroller;

import com.dependapot.bases.Fort_Sill_Map;
import com.dependapot.character.Dependa;
import com.dependapot.textparser.ParseResponse;
import com.dependapot.textparser.TextParser;
import com.dependapot.tutorial.Welcome;

import java.util.*;

public class GameController {
    private static ParseResponse response = null;
    private static TextParser parser = null;
    private static Fort_Sill_Map fortSill = new Fort_Sill_Map("Fort Sill", "Some post");
    private static Scanner input = new Scanner(System.in);

    // dependa
    // Jon has to give us the object
    // but for now, we are just creating the object for testing purpose;
    private static Dependa player =  new Dependa("Dependa 1", "face", "gate");

    private static void interactWithNPC(String noun) {
        String playerCurrentLocation = player.getLocation();
        // check the soldier's existence

    }

    private static void displayBuildings(String noun){
        switch (noun) {
            default:
                System.out.println("These are the possible location you can go!!");
                System.out.println(fortSill.getBuildings());
                break;
        }
    }

    private static void enteringBuildingController(String noun) {
        switch (noun) {
            case "dfac":
            case "px":
            case "church":
            case "gym":
            case "barracks":
            case "commisary":
                System.out.println("EnteringBuildingController: " + noun);
                fortSill.enterToBuilding(noun);
                setDependaLocation(noun);
                System.out.println("Curent " + player.getName() + "'s location: " + player.getLocation());
                break;
            default:
                System.out.println("These are the possible location you can go!!");
                System.out.println(fortSill.getBuildings());
                break;
        }
    }

    private static void setDependaLocation(String location) {
        player.setLocation(location);
    }

    public static void main(String[] args) {
        // welcome intro
        Welcome.intro();
        parser = new TextParser();

        // below this line while loop
        String userAction = "";
        while(!userAction.equals("exit") && !userAction.equals("quit") ) {
            System.out.println("Enter your action [format= verb + noun]");
            userAction = input.nextLine();
            response = parser.receiveAction(userAction, fortSill.getName());
            if ( !(response.getVerb().equals("")) || !(response.getNoun().equals("")))  {
                System.out.println("Verb: " + response.getVerb());
                switch (response.getVerb().trim()) {
                    case "go":
                    case "move":
                    case "drive":
                    case "walk":
                    case "run":
                        GameController.enteringBuildingController(response.getNoun());
                        break;
                    case "display":
                    case "show":
                        GameController.displayBuildings(response.getVerb());
                        break;
                    case "talk":
                    case "approach":
                    case "interact":
                        break;
                    default:
                        System.out.println("Verb " + response.getVerb());
                        System.out.println("Noun: " + response.getNoun());
                        break;
                } // end of swtich
            } // end of if statement
        } // end of while loop

//        while(){ loop through building
//          see the soldiers. What would you like to do next? Move another building
//        talk to soldiers. Dependa says exit/quit to leave game
//        }

    }
}
