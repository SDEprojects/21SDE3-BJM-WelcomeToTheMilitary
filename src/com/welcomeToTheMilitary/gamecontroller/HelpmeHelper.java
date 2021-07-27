package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.MainDisplay;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class HelpmeHelper {
    public static String printHelpRequestDataFromJSON(ServiceMember usr) {
        JSONParser parser = new JSONParser();
        StringBuilder helpString = new StringBuilder();

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
                helpString.append(eachInstruction + " verb " + instructionSet.get("verb").toString()).append("\n");
                helpString.append( eachInstruction + " noun " + instructionSet.get("noun").toString()).append("\n");
                helpString.append( eachInstruction  + instructionSet.get("example").toString()).append("\n").append("\n");

            });
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        helpString.append("input data in noun + verb format and hit enter").append("\n").append("\n");
        return helpString.toString();
    }
}
