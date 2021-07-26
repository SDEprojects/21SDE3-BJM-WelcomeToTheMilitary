package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.BaseMap;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.mainDisplay;
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
    public static String showController(ServiceMember member, BaseMap currentMap) {

        return "Current Post: " + member.getPostName() + "\n" +
                "Location: " + member.getLocation() + "\n" +
                "Buildings: " + currentMap.listBuildingsForMap()+ "\n"

                ;
    };

    public static String status(ServiceMember usrDep) {
        return usrDep.getRank().getAbbreviation() + " "+ usrDep.getName() + "\n" +
                "Special: " + usrDep.getSpecial()+ "\n" +
                "Health: " + usrDep.getHealth() + "\n" +
                "Strength: " + usrDep.getStrength() + "\n" +
                "Inventory: \n" + usrDep.listItemsForStats();
    }

    public static void enteringBuildingController(String noun, ServiceMember usrSM, BaseMap currentMap) throws IOException, ParseException {
        String lowerNoun = noun.toLowerCase();
        usrSM.setLocation(lowerNoun);
        currentMap.displaySoldiers(usrSM.getLocation());

        System.out.println("Entering: " + noun + " building");
        System.out.println("Current " + usrSM.getName() + "'s location: " + usrSM.getLocation());

    }
}
