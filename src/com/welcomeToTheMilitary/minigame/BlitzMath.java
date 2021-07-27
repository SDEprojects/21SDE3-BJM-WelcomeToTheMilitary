package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.MainDisplay;

import java.util.Scanner;

public class BlitzMath implements iMinigame {

    private int MIN = 6;
    private int MAX = 19;
    private int playerScore = 0;
    private int seconds = 18000;
    public StringBuilder stringBuilderMath = new StringBuilder();

    public boolean addThis() throws InterruptedException {
        //clear userAction method
        MainDisplay.setUserAction("");
        //create Stringbuilder obj to append text for gui
        stringBuilderMath = new StringBuilder();
        //create and initialize math random objects to be added by user
        double random1 = Math.random();
        double random2 = Math.random();
        //logic to add random numbers and get sum
        int rand1 = (int) (random1 * ((MAX - MIN) + 1)) + MIN;
        int rand2 = (int) (random2 * ((MAX - MIN) + 1)) + MIN;
        //print to terminal the 2 random numbers
        System.out.println("What is " + rand1 + " + " + rand2);
        //print to gui for user
        MainDisplay.setMainTextArea("What is " + rand1 + " + " + rand2 + '\n' + " You have " + seconds / 2000 + " seconds to answer");

        //sleep to allow time for user to read
//        Thread.sleep(6000);

        //run timer method
        int playerAnswer = playerAnswerOnTime();

        if (playerAnswer == rand1 + rand2) {
            System.out.println("You got it");
            MainDisplay.setMainTextArea("You got it");
            return true;
        } else {
            System.out.println("Wrong!");
            MainDisplay.setMainTextArea("Wrong");
            return false;
        }
    }

    public int playerAnswerOnTime() throws InterruptedException {
        /* define start and end time for while loop
        start time */
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        // end time for while loop
        long endTime = startTime + seconds;
        // while loop will end from difference
        long difference = (endTime - startTime);

        int playerAnswer = -1;
        MainDisplay.setUserAction(String.valueOf(playerAnswer));
        //while loop with timer to exit

            try {
                //parse user answer and convert from String to integer

                while (playerAnswer == -1) {

                    playerAnswer = Integer.parseInt(MainDisplay.getUserAction());
                    if (System.currentTimeMillis() > endTime) {
                        break;
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Not a valid input");
                MainDisplay.setMainTextArea("Not a valid input");

                return -1;
            }

        return playerAnswer;
    }

    @Override
    public boolean play() throws InterruptedException {
        return addThis();
    }

    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }
}
