package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.BaseMap;
import com.welcomeToTheMilitary.character.ServiceMember;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Display {

    public static void showController(String noun, ServiceMember usrDep, BaseMap currentMap) {

            switch (noun) {
                case "item":
                    System.out.println(usrDep.getItems());
                    return;
                case "map":
                case "buildings":
                    System.out.println(currentMap.getBuildings());
                    return;
                case "building":
                case "location":
                    // System.out.println(fo)
                    System.out.println("You are currently in " + usrDep.getLocation() + ", at Fort Sill");
                    // System.out.println("You s");
                    return;
                case "status":
                    //if "status" is noun, it'll call player's current status
                    status(usrDep);
                    return;

                default:
                    System.out.println("These are the possible location you can go!!");
                    break;
            }

    }

    public static void status(ServiceMember usrDep) {
        System.out.println("=".repeat(10) + " Status Report " + "=".repeat(10));
        System.out.println("Player name: " + usrDep.getName());
        System.out.println("Player rank: " + usrDep.getRank());
        System.out.println("Player's special: " + usrDep.getSpecial());
        System.out.println("Player health: " + usrDep.getHealth());
        System.out.println("Player attack damage: " + usrDep.getStrength());
        System.out.println("Player's inventory:");
        System.out.println("Player's Permanent Change of Station request: " + usrDep.getPcsRequest());
        System.out.println(usrDep.getItems().toString());
    }

    public static void enteringBuildingController(String noun, ServiceMember usrSM, BaseMap currentMap) throws IOException, ParseException {
        String lowerNoun = noun.toLowerCase();
        usrSM.setLocation(lowerNoun);

        currentMap.displaySoldiers(usrSM.getLocation());
        System.out.println("Entering: " + noun + " building");
        System.out.println("Current " + usrSM.getName() + "'s location: " + usrSM.getLocation());

    }
}
