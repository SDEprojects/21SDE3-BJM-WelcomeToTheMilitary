package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.character.ServiceMember;

import java.util.Scanner;

public class DDRKeyboard implements iMinigame {
    private boolean isWin = false;
    private String DDR_OPTION[] = {"a", "s", "w", "d", "x"};
    private static int MIN = 0;
    private static int MAX = 5;

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

    // get random
    private int getRandom(int MIN, int MAX) {
        int randomIndex = (int) (Math.random() * (MAX - MIN));
        return randomIndex;
    }

    private String getDDRInput() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Type the word that was displayed:\n> ");
        String inputData = scan.nextLine();
        return inputData;
    }

    // some thread to count down and clear the screen
    private void countScreenTimerMemorize() {
        try {
            for (int timer = 3; timer > 0; timer--) {
                Thread.sleep(700);
                System.out.println("Count Down to memorize: " + timer);
            }
            // clear screen
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
    public boolean play() {
        //setIsWin(play(getRandom(MIN, MAX))); //this is setting a true or false
        return playMe();
    }

    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }

    // private playDDR to hide logic from public
    private boolean playMe() {
        int random = getRandom(MIN, MAX);
        StringBuilder strBuilder = new StringBuilder(DDR_OPTION[random]);
        for (int i = 0; i < 3; i++) {
            // display word to type
            System.out.println("Memorize the String..");
            System.out.println(strBuilder.toString());
            // make a counter 3, 2, 1 then clear the screen
            countScreenTimerMemorize();
            // get an input from user
            String ddrInput = getDDRInput();
            // append next word
            if (strBuilder.toString().equals(ddrInput)) {
                System.out.println("Match");
                if(i == 2) return true;
            } else {
                return false;
            }
            // get another random text and append it to str builder
            int randomIndex = getRandom(MIN, MAX);
            strBuilder.append(DDR_OPTION[randomIndex]);
        }
        return false;
    }
}
