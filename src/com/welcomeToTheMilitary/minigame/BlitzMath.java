package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.character.ServiceMember;

import java.util.Scanner;

public class BlitzMath implements iMinigame{

    //test main
    public static void main(String[] args) {

        BlitzMath test = new BlitzMath();
        test.addThis();
    }

    private int MIN = 6;
    private int MAX = 19;
    private int playerScore = 0;

    public boolean addThis(){
        double random1 = Math.random();
        double random2 = Math.random();

        int rand1 = (int)(random1 * ((MAX - MIN) + 1)) + MIN;
        int rand2 = (int)(random2 * ((MAX - MIN) + 1)) + MIN;

        System.out.println("What is " + rand1 + " + " + rand2);

        int playerAnswer = playerAnswerOnTime();

        if(playerAnswer == rand1 + rand2){
            System.out.println("You got it");
            return true;
        }
        else{
            System.out.println("Wrong!");
            return false;
        }
    }

    public int playerAnswerOnTime() {
        System.out.println("Add These");
        System.out.println("You have 6 seconds to answer");

        //sets the start time
        long startTime = System.currentTimeMillis();

        Scanner userInput = new Scanner(System.in);

        try{
            int playerAnswer = Integer.parseInt(userInput.nextLine());
            //sets the end time
            long endTime = System.currentTimeMillis();

            long difference = (endTime - startTime);

            //if its less than 6 seconds
            if(difference < 6000){
                return playerAnswer;
            }
            else System.out.println("Timer is up");
            return -1;
        }
        catch(NumberFormatException e){
            System.out.println("Not a valid input");
        }
        return -1;
    }

    @Override
    public boolean play() {
        return addThis();
    }

    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }
}
