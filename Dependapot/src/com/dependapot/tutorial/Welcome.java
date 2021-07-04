package com.dependapot.tutorial;


import com.dependapot.character.Dependa;

import java.util.ArrayList;
import java.util.Scanner;

public class Welcome {

    static ArrayList<String> spellList = new ArrayList<>();

//    public static void main(String[] args) {
//        spellList.add("Multi-Cam Purse");
//        spellList.add("Bumper Sticker");
//        spellList.add("LuLuRoe Business Card");
//        intro();
//    }

    public static Dependa intro(){
        clearConsole();
        System.out.println("Welcome to the Military!");
        System.out.println("Would you like to use the tutorial?");
        Scanner tutorial = new Scanner(System.in);
        String tutorialAnswer = tutorial.nextLine().toLowerCase();
        while (!tutorialAnswer.equals("yes") && !tutorialAnswer.equals("no") && !tutorialAnswer.equals("y")
                && !tutorialAnswer.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            tutorialAnswer = tutorial.nextLine();
        }
        if (tutorialAnswer.equals("yes") || tutorialAnswer.equals("y")){
            clearConsole();
            questions();
        }
        System.out.println("Would you like see a description of available spells?");
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
        System.out.println("Now that you are familiar with the game lets go ahead and make your Warrior avatar.");

        String spellReturn = picSpells();

        System.out.println("Press anything to continue");
        tutorial.nextLine();
        clearConsole();

        System.out.println("Please type in your Warrior's name: ");
        String dependaName = tutorial.nextLine();


        Dependa myDependa = new Dependa(dependaName,spellReturn,"Fort Sill");

        System.out.println("\n\nYour name is: " + myDependa.getName() + " and your specialty is " + myDependa.getSpecial());

        return myDependa;

    }

    public static void questions() {
        System.out.println("This tutorial will teach you the basics of the game." +
                "\nFirst a test to gauge your knowledge:");
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
            System.out.println("A military service member or in your case a Warrior\n" +
                    "who are recruited or conscripted to a military branch:\n" +
                    "army, navy, marines, air force and sometimes the coas guard.\n"+
                    "After testing you are provided a certain number of jobs. Choose wisely.\n" +
                    "");
        }
        System.out.println("Question 2: Do you know and understand the Army enlisted rank structure?");
        String questionAnswerTwo = questionOne.nextLine().toLowerCase();
        while (!questionAnswerTwo.equals("yes") && !questionAnswerTwo.equals("no") && !questionAnswerTwo.equals("y")
                && !questionAnswerTwo.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            questionAnswerTwo = questionOne.nextLine();
        }
        // if the user enters no they are given a rundown of the military rank structure, otherwise if answered yes it moves onto the next question.
        if (questionAnswerTwo.equals("no") || questionAnswerTwo.equals("n")){
            clearConsole();
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
        }
        // if the user enters no they are given a brief overview of what the goal of the game is.
        System.out.println("Question 3: Do you understand the goal of this game? ");
        String questionAnswerThree = questionOne.nextLine().toLowerCase();
        while (!questionAnswerThree.equals("yes") && !questionAnswerThree.equals("no") && !questionAnswerThree.equals("y")
                && !questionAnswerThree.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            questionAnswerThree = questionOne.nextLine();
        }
        if (questionAnswerThree.equals("no") || questionAnswerThree.equals("n")){
            clearConsole();
            System.out.println("You start off as a warrior. Your goal is to climb the ranks any way possible to\n" +
                    "achieve the highest rank available to become the strongest Warrior in the Army.\n" +
                    "Every warrior starts off at Fort Sill with 100 points of health and chose 1 of three spells. \n" +
                    "");
        }
        intro();
    }

    public static void spells(){
        System.out.println("Here are the spell you can chose from: \n" +
                "Dog Tags: You always wear your dog tags outside fo your shirt. Your military prides deflects attacks.\n" +
                "Military Skull Tattoo: After Basic Training you got a tattoo of your company \"Army Strong! Queen of Battle!\n" +
                "High-n-Tight: Your hair is within regulations and it increases your attacks.");
    }

    public static String picSpells(){
        spellList.add("Dog Tags");
        spellList.add("Military Skull Tattoo");
        spellList.add("High-n-Tight");
        int whileInt = 0;
        while(whileInt == 0){

            System.out.println("Please type in the number associated with the spell you would like to start with: ");
            int x = 1;
            while( x< spellList.size() +1){
                System.out.println(x + ". " + spellList.get(x - 1));
                x++;
            }

            Scanner spells = new Scanner(System.in);
            int spellSelection = spells.nextInt();

            if(spellSelection > 0 && spellSelection < spellList.size() +1) {
                spellSelection--;
                return spellList.get(spellSelection);
            }
        }

        return "";
    }
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");}
}

