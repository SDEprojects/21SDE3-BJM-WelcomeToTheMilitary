package com.welcomeToTheMilitary.tutorial;


import com.welcomeToTheMilitary.character.ServiceMember;

import com.welcomeToTheMilitary.gui.MainDisplay;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Welcome {

    static ArrayList<String> spellList = new ArrayList<>();
    static HashMap<String,String> specialHash = new HashMap<>();
    private static JsonReader jReader;
    public static void separatorTitle(){
        System.out.println("=========================================================================");;
    }

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {

        intro(spellList);
    }

    public static ServiceMember intro(ArrayList<String> _spellList) throws InterruptedException, IOException, ParseException {
        jReader = new JsonReader();
        specialHash = jReader.getSpecials();
        spellList = _spellList;
        clearConsole();
        //print out new lines to add some space
        System.out.println("\n"+ "\n" + "\n");
        //print out ASCII art of Salute
        System.out.println("       .---.\n" +
                "  ___ /_____\\\n" +
                " /\\.-`( '.' )\n" +
                "/ /    \\_-_/_\n" +
                "\\ `-.-\"`'V'//-.\n" +
                " `.__,   |// , \\\n" +
                "     |Ll //Ll|\\ \\\n" +
                "     |__//   | \\_\\\n" +
                "    /---|[]==| / /\n" +
                "    \\__/ |   \\/\\/\n" +
                "    /_   | Ll_\\|\n" +
                "     |`^\"\"\"^`|\n" +
                "     |   |   |\n" +
                "     |   |   |\n" +
                "     |   |   |\n" +
                "     |   |   |\n" +
                "     L___l___J\n" +
                "      |_ | _|\n" +
                "     (___|___)\n" +
                "      ^^^ ^^^");
        System.out.println("\n");
        System.out.println("Welcome to the Military!");
        Thread.sleep(3000);
        Welcome.separatorTitle();;
        System.out.println("Would you like to use the tutorial?");
        Welcome.separatorTitle();
        Scanner tutorial = new Scanner(System.in);

        //Start GUI!
        MainDisplay.setMainTextArea("Would you like to use the tutorial? \n Please enter Yes/y or No/no");
        //
        String tutorialAnswer = tutorial.nextLine().toLowerCase();
//        String tutorialAnswer = mainDisplay.getUserAction();

        // While loop below Only necessary on console, not gui
        while (!tutorialAnswer.equals("yes") && !tutorialAnswer.equals("no") && !tutorialAnswer.equals("y")
                && !tutorialAnswer.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            tutorialAnswer = tutorial.nextLine();
        }

        if (tutorialAnswer.equals("yes") || tutorialAnswer.equals("y")){
            clearConsole();
            questions();

        }
        separatorTitle();
        System.out.println("Would you like see a description of available spells?");
        separatorTitle();
        String spellAnswer = tutorial.nextLine().toLowerCase();
        while (!spellAnswer.equals("yes") && !spellAnswer.equals("no") && !spellAnswer.equals("y")
                && !spellAnswer.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            spellAnswer = tutorial.nextLine();}
        if (spellAnswer.equals("yes") || spellAnswer.equals("y")){
            clearConsole();
            spells();
        }
        System.out.println("Press anything to continue");
        tutorial.nextLine();
        separatorTitle();
        System.out.println("Now that you are familiar with the game lets go ahead and make your Warrior avatar.");
        separatorTitle();
        String spellReturn = picSpells();

        System.out.println("Press anything to continue");
        tutorial.nextLine();
        clearConsole();


        separatorTitle();
        System.out.println("Please type in your Warrior's name: ");
        separatorTitle();
        String dependaName = tutorial.nextLine();


        ServiceMember myServiceMember = new ServiceMember(dependaName,spellReturn,"Fort Sill");

        System.out.println("\n\nYour name is: " + myServiceMember.getName() + " and your specialty is " + myServiceMember.getSpecial());

        return myServiceMember;

    }

    public static void questions() {
        Welcome.separatorTitle();
        System.out.println("This tutorial will teach you the basics of the game." +
                "\nFirst a test to gauge your knowledge:");
        Welcome.separatorTitle();
        System.out.println("Question 1: Do you know what a military service memeber is? ");
        Scanner questionOne = new Scanner(System.in);
        String questionAnswerOne = questionOne.nextLine().toLowerCase();
        while (!questionAnswerOne.equals("yes") && !questionAnswerOne.equals("no") && !questionAnswerOne.equals("y")
                && !questionAnswerOne.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            questionAnswerOne = questionOne.nextLine();
        }
        // if the user enters no they are given a definition of dependapotomus. If answer is yes user is asked the next question
        if (questionAnswerOne.equals("no") || questionAnswerOne.equals("n")) {
            clearConsole();
            Welcome.separatorTitle();
            System.out.println("A military service member or in your case a Warrior\n" +
                    "who are recruited or conscripted to a military branch:\n" +
                    "army, navy, marines, air force and sometimes the coas guard.\n"+
                    "After testing you are provided a certain number of jobs. Choose wisely.\n" +
                    "");
            Welcome.separatorTitle();
        }
        Welcome.separatorTitle();
        System.out.println("Question 2: Do you know and understand the Army enlisted rank structure?");
        Welcome.separatorTitle();
        String questionAnswerTwo = questionOne.nextLine().toLowerCase();
        while (!questionAnswerTwo.equals("yes") && !questionAnswerTwo.equals("no") && !questionAnswerTwo.equals("y")
                && !questionAnswerTwo.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            questionAnswerTwo = questionOne.nextLine();
        }
        // if the user enters no they are given a rundown of the military rank structure, otherwise if answered yes it moves onto the next question.
        if (questionAnswerTwo.equals("no") || questionAnswerTwo.equals("n")){
            clearConsole();
            Welcome.separatorTitle();
            System.out.println("The Army enlisted rank structure consists of 9 grades from E-1 to E-9, and 13 ranks are held within those grades. Some grades have multiple ranks.\n" +
                    "The grades and ranks are as follows:  \n" +
                    "E-1/Private\n" +"E-2/Private Second Class\n" +
                    "E-3/Private First Class\n" +
                    "E-4/Corporal or Specialist\n" +
                    "E-5/Sergeant\n" +
                    "E-6/Staff Sergeant\n" +
                    "E-7/Sergeant First Class\n" +
                    "E-8/Master Sergeant or First Sergeant\n" +
                    "E-9/Command Sergeant Major or Sergeant Major of the Army\n" +
                    "First Sergeant and Sergeant Major of the Army are both ranks that one must be appointed to and are limited to a select few.\n" +
                    "");
            Welcome.separatorTitle();
        }
        // if the user enters no they are given a brief overview of what the goal of the game is.
        Welcome.separatorTitle();
        System.out.println("Question 3: Do you understand the goal of this game? ");
        String questionAnswerThree = questionOne.nextLine().toLowerCase();
        while (!questionAnswerThree.equals("yes") && !questionAnswerThree.equals("no") && !questionAnswerThree.equals("y")
                && !questionAnswerThree.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            questionAnswerThree = questionOne.nextLine();
        }
        if (questionAnswerThree.equals("no") || questionAnswerThree.equals("n")){
            clearConsole();
            Welcome.separatorTitle();
            System.out.println("You start off as a warrior. Your goal is to climb the ranks any way possible to\n" +
                    "achieve the highest rank available to become the strongest Warrior in the Army.\n" +
                    "Every warrior starts off at Fort Sill with 100 points of health and chose 1 of three spells. \n" +
                    "");
            Welcome.separatorTitle();
            System.out.println("Press anything to continue");
            questionOne.nextLine();
        }
//        intro(spellList);
    }

    public static void spells(){
        Welcome.separatorTitle();
        System.out.println("Here are the specials you can chose from: \n" +
                "~".repeat(41));

        Iterator it = specialHash.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + ": " + pair.getValue());
            System.out.println("-".repeat(50));

        }
    }

    public static String picSpells(){

        int whileInt = 0;
        while(whileInt == 0){

            System.out.println("Please type in the number associated with the special you would like to start with: ");
            int x = 1;

            for (String key : specialHash.keySet()) {
                System.out.println(x + ". " + key);
                spellList.add(key);
                x++;
            }

            Scanner spells = new Scanner(System.in);
            try{
                int spellSelection = spells.nextInt();

                if(spellSelection > 0 && spellSelection < spellList.size() +1) {
                    spellSelection--;
                    return spellList.get(spellSelection);
                }
            }catch (InputMismatchException ignored){

            }
        }
        return "";
    }
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");}
}
