package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.Main;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.MainDisplay;

import java.util.Random;
import java.util.Scanner;

public class DDRKeyboard implements iMinigame {
    private boolean isWin = false;
    private String DDR_OPTION[] = {"a", "s", "w", "d", "x"};
    private static int MIN = 0;
    private static int MAX = 5;

    private StringBuilder output = new StringBuilder();

    public DDRKeyboard() {
        instruction();
    }

    //@Override
    public boolean getIsWin() {
        return isWin;
    }

    private void setIsWin(boolean _isWin) {
        this.isWin = _isWin;
    }

    private void instruction() {
        System.out.println("=".repeat(20));
        System.out.println("Challenge");
        System.out.println("=".repeat(20));
    }
    private String randomStrings(){
        StringBuilder random = new StringBuilder();
        Random randomIndex = new Random();
        for(int i = 0; i < DDR_OPTION.length; i++){
            random.append(DDR_OPTION[randomIndex.nextInt(DDR_OPTION.length)]);
        }
        return random.toString();
    }

    // get random
    private int getRandom(int MIN, int MAX) {
        int randomIndex = (int) (Math.random() * (MAX - MIN));
        return randomIndex;
    }

//    private String getDDRInput() throws InterruptedException {
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Type the word that was displayed:\n> ");
//        MainDisplay.setMainTextArea(output.append("Type the word that was displayed:\n").toString());
//        String inputData = MainDisplay.getUserAction();
//        output.setLength(0);
//        Thread.sleep(3000);
//        return inputData;
//    }

    // some thread to count down and clear the screen
    public void countScreenTimerMemorize() {
        try {
            for (int timer = 5; timer > 0; timer--) {
                Thread.sleep(1000);
                System.out.println("Count Down to memorize: " + timer);
                MainDisplay.setMainTextArea(output.append("Timer expires in: " + timer + "\n").toString());
            }
            Thread.sleep(1000);
            // clear screen
            output.setLength(0);
            clearScreen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // clear the screen
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public boolean play() throws InterruptedException {
        //setIsWin(play(getRandom(MIN, MAX))); //this is setting a true or false
        return playMe();
    }

    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }

    // private playDDR to hide logic from public
    private boolean playMe() throws InterruptedException {

        MainDisplay.setUserAction("");
        String stringToGuess = randomStrings();

            // display word to type
            System.out.println("Memorize the String..");
            MainDisplay.setMainTextArea(output.append("Hurry and type this string!\n").toString());
            System.out.println(stringToGuess);
            MainDisplay.setMainTextArea(output.append(stringToGuess + "\n").toString());
            // make a counter 3, 2, 1 then clear the screen
            countScreenTimerMemorize();
            // get an input from user
            String ddrInput = MainDisplay.getUserAction();
            // append next word
        if(!stringToGuess.equals(ddrInput)) {
            MainDisplay.setMainTextArea(output.append("Time's up! Better luck next time").toString());
            Thread.sleep(3000);
            return false;
        }
        else {
            System.out.println("Match");
            MainDisplay.setMainTextArea(output.append("It's a match. Great job!").toString());
            Thread.sleep(3000);
            return true;
        }

    }
}
