package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class HelpmeHelper {
    public static void printHelpRequestDataFromJSON(ServiceMember usr) {
        JSONParser parser = new JSONParser();
        InputStream inputFilePossibleVerbAndNounJSON = JsonReader.class.getResourceAsStream("/possibleVerbAndNoun.json");
        try (FileReader reader = new FileReader( "jsonFiles/possibleVerbAndNoun.json"))
        //try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFilePossibleVerbAndNounJSON, "UTF-8")))
        {
            Object helpObjectObj = parser.parse(reader);
            JSONObject helpObjectHelpme = (JSONObject) helpObjectObj;
            JSONObject helpObject = (JSONObject) helpObjectHelpme.get(usr.getPostName());
            // helpObject = (JSONObject) parser.parse(new FileReader("jsonFiles/possibleVerbandNoun.json"));
            helpObject.keySet().forEach(eachInstruction -> {
                System.out.println("=".repeat(5) + " " + eachInstruction + " " + "=".repeat(5));
                JSONObject instructionSet = (JSONObject) helpObject.get(eachInstruction);
                System.out.println("Supported " + eachInstruction + " verb " + instructionSet.get("verb").toString());
                System.out.println("Supported " + eachInstruction + " noun " + instructionSet.get("noun").toString());
                System.out.println("Example " + eachInstruction + " example " + instructionSet.get("example").toString() + "\n");
            });
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    //Maybe delete this code?  It only calls the printHelpRequestDataFromJSON method
    public static void interactHelpRequest(String noun, ServiceMember usrDep) {
        switch (noun) {
//            case"exit":
//            case "quit":
//                System.out.println("Thanks for playing");
//                System.exit(0);
            default:
                HelpmeHelper.printHelpRequestDataFromJSON(usrDep);
        }
    }
}
