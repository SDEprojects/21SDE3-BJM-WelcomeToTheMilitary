package com.welcomeToTheMilitary.minigame;
import com.welcomeToTheMilitary.Main;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.MainDisplay;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class PTGame {

    private static Scanner userAction = new Scanner(System.in);
    private String[] exercise = {"pull up", "push up", "3 mile run", "sit up", "lunge", "bend and reach"};

    public PTGame() {

    }

    public void doPt(ServiceMember member) throws InterruptedException {
        if (member.getLocation().equals("gym")) {
            if (member.getFitnessCounter() >= 3){
                System.out.println("You are too sore to PT right now");
                MainDisplay.setMainTextArea("You are too sore to PT right now");
            } else {
                //Member's fitness counter increase by 1
                member.setFitnessCounter(member.getFitnessCounter()+1);

                int successCount = 0;
                boolean runPt = true;
                //run PT loop
                while (runPt){
                    //displays a random exercise string.
                    String userCommand = "";
                    MainDisplay.setUserAction("");

                    Random randInt = new Random();
                    int randIndex = randInt.nextInt(exercise.length);

                    String currentExercise = exercise[randIndex];

                    System.out.println("repeat the exercise: " + currentExercise);
                    MainDisplay.setMainTextArea("Repeat the current exercise by typing exactly: " + currentExercise);
//                    String userCommand = userAction.nextLine();
                    while (userCommand.equals("")) {
                        userCommand = MainDisplay.getUserAction();
                        Thread.sleep(3000);
                    }


                    //If user types in the exact string, the exercise will be a success
                    if (!userCommand.equals("")) { //prevents empty string


                         if (userCommand.equals(currentExercise)) {
                            System.out.println("Nice work!");
                            MainDisplay.setMainTextArea("Nice work soldier!");
                            Thread.sleep(3000);
                            successCount++;
                        } else {
                            //if user mistypes, the success counter will go down
                            System.out.println("You didn't do proper form!");
                            MainDisplay.setMainTextArea("You didn't do proper form!");
                             Thread.sleep(3000);
                            successCount--;
                        }

                        //if user gets 5 correct, player will get increased strength
                        if (successCount >= 3) {
                            member.setStrength(member.getStrength()+1);
                            System.out.println("You're feeling buff! Strength increased by 1");
                            MainDisplay.setMainTextArea("You're feeling buff! Strength increased by 1");
                            Thread.sleep(3000);
                            runPt = false;
                        }
                    }
                    //if user drops below 0, PT loop ends
                    if (successCount < 0 ) {
                        System.out.println("You suck at PT soldier!");
                        int lostHealth = 5;
                        member.setHealth(lostHealth, false);
                        System.out.println("You damaged your health by " + lostHealth);
                        MainDisplay.setMainTextArea("You suck at PT soldier! \n" +
                                "You damaged your health by " + lostHealth);
                        Thread.sleep(3000);
                        runPt = false;
                    }

                }
                //increase strength
            }

        } else {
            System.out.println("The installation commander has directed that you can only PT at the Gym!");
            MainDisplay.setMainTextArea("The installation commander has directed that you can only PT at the Gym!");
        }
    }

}
