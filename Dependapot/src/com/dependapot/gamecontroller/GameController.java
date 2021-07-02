package com.dependapot.gamecontroller;

import com.dependapot.bases.Fort_Sill_Map;
import com.dependapot.character.Dependa;
import com.dependapot.character.LowerEnlist;
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

    // minigame
    private static iMinigame rockPaperScissors = new RPC();

    // dependa
    // Jon has to give us the object
    // but for now, we are just creating the object for testing purpose;
    private static Dependa player =  new Dependa("Dependa 1", "face", "gate");

    private static void interactWithNPC(String noun) {
        String playerCurrentLocation = player.getLocation();
        // check the soldier's existence
        ArrayList<LowerEnlist> existingSolider = fortSill.getSolider(playerCurrentLocation);
        if (existingSolider.size() == 0) {
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
            boolean isWin = rockPaperScissors.play();
            return;
        }
        System.out.println("Cannot find the soldier you are looking for");
        return;
    }

    private static void displayBuildings(String noun){
        switch (noun) {
//            add buildings
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
            case "market":
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
                        GameController.displayBuildings(response.getNoun());
                        break;
                    case "talk":
                    case "approach":
                    case "interact":
                        GameController.interactWithNPC(response.getNoun());
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
