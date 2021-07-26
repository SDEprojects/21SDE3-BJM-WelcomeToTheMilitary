package com.welcomeToTheMilitary.tutorial;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.mainDisplay;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NewWelcome {


    private HashMap<String,String> specialHash = JsonReader.getSpecials();

    public NewWelcome() {
    }

    public String[] intro() throws InterruptedException, IOException, ParseException {


        String tutorialAnswer = mainDisplay.getUserAction();

        while (tutorialAnswer.equals("")) {
            mainDisplay.setMainTextArea("Would you like to use the tutorial? \n Please enter Yes/y or No/no");
            if (tutorialAnswer.equals("yes") || tutorialAnswer.equals("y")) {
                mainDisplay.setUserAction("");
                //Ask questions

                //Tutorial is now all or nothing
                mainDisplay.setMainTextArea(questionOne());
                Thread.sleep(2000);

                //
                mainDisplay.setMainTextArea(questionTwo());
                Thread.sleep(3000);

                //
                mainDisplay.setMainTextArea(questionThree());
                Thread.sleep(3000);


            }
            else {
                if (tutorialAnswer.equals("no") || tutorialAnswer.equals("n")) {

                    break;
                }
            }

        }
        mainDisplay.setUserAction("");

        String spellAnswer = mainDisplay.getUserAction();

        do {
            mainDisplay.setMainTextArea(spells());
        } while (!spells().contains(spellAnswer));


        mainDisplay.setUserAction("");//clear once again

        String memberName = mainDisplay.getUserAction();

        do {
            mainDisplay.setMainTextArea("Please enter your name!");
        } while (memberName.equals(""));

        String[] memberData = {memberName,spellAnswer,"Fort Bliss"};
        return memberData;
    }

    public String questionOne() {
        return "A Military service member is a warrior who is recruited (or conscripted) to a military branch: \n" +
                "Either the Army, Navy, Marines, Air Force, Coast Guard or even the Space Force!";
    }

    public String questionTwo() {
        return "The Army enlisted rank structure consists of 9 grades from E-1 to E-9, and 13 ranks are held within those grades. Some grades have multiple ranks.\n" +
        "The grades and ranks are as follows:  \n" +
                "E-1/Private\n" +"E-2/Private Second Class\n" +
                "E-3/Private First Class\n" +
                "E-4/Corporal or Specialist\n" +
                "E-5/Sergeant\n" +
                "E-6/Staff Sergeant\n" +
                "E-7/Sergeant First Class\n" +
                "E-8/Master Sergeant or First Sergeant\n" +
                "E-9/Command Sergeant Major or Sergeant Major of the Army\n" +
                "First Sergeant and Sergeant Major of the Army are both ranks that one must be appointed to and are limited to a select few.\n";
    }

    public String questionThree() {
        return "You start off as a warrior. Your goal is to climb the ranks any way possible to" +
        "achieve the highest rank available to become the strongest Warrior in the Army.\n" +
                "Every warrior starts off at Fort Sill with 100 points of health and chose 1 of three spells. \n"
                ;
    }

    public  String spells(){
        StringBuilder spellList = new StringBuilder();

        spellList.append("Here are the specials you can chose from: \n");

        Iterator it = specialHash.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            spellList.append(pair.getKey()).append(" : ").append(pair.getValue()).append("\n").append("_".repeat(45));
        }

        return spellList.toString();
    }

}
