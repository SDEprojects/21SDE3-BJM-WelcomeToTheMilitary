package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.Main;
import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.MainDisplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class CrackTheCode implements iMinigame{

    @Override
    public boolean play() throws InterruptedException {
        return playMe();
    }

    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }

    private String[] codes = {"passwords", "privacy", "computer", "messages", "information"};


    public String getRandomWord(){
        double rand = Math.random();

        int randomIndex = (int)(rand * ((codes.length - 1) + 1));
        String randomWord = codes[randomIndex];

        return randomWord;
    }

    public String maskTheWord(String input){
        String masked = "";
        for(int i = 0; i < input.length(); i++){
            masked+="*";
        }
        return masked;
    }

    public boolean playMe() throws InterruptedException {

        MainDisplay.setUserAction(" ");

        StringBuilder output = new StringBuilder();

        System.out.println("You have 10 tries to guess the word!");
        MainDisplay.setMainTextArea(output.append("You have 10 tries to guess the word!\n").toString());
        MainDisplay.setMainTextArea(output.append("You have 5 seconds to keep guessing letters or\nit'll count towards your guess\n").toString());
        output.setLength(0);
        Thread.sleep(10_000);

        //has to start at -1 because it gets the first char of battle
        int tries = -1;

        String randomWord = getRandomWord();

        char[] lettersToGuess = randomWord.toCharArray();
        char[] outputWord = maskTheWord(randomWord).toCharArray();
        HashSet<Character> lettersGuessed = new HashSet<>();

        lettersGuessed.remove(" ");

        System.out.println(outputWord);

            while(!Arrays.equals(lettersToGuess, outputWord)){
                Thread.sleep(1000);
                try{
                    MainDisplay.setMainTextArea(output.append("Enter a letter\n").toString());

                    //player's letter guess
                    char userInput = MainDisplay.getUserAction().charAt(0);
                    lettersGuessed.add(userInput);

                    tries++;

                    int duplicateLetters = 0;

                    for(int i = 0; i < lettersToGuess.length; i++){

                        if(lettersToGuess[i] == userInput){
                            duplicateLetters++;
                            outputWord[i] = userInput;
                            //if player guesses a correct letter, a counter wont be added
                            tries--;
                        }
                    }
                    //handles multiple letters in a word
                    if(duplicateLetters > 1){
                        tries+= duplicateLetters;
                        tries--;
                    }

                    System.out.println("Current tries: " + tries +"\n");
                    MainDisplay.setMainTextArea(output.append("Current tries: " + tries  + "\n").toString());
                    System.out.println(outputWord);
                    MainDisplay.setMainTextArea(output.append(Arrays.toString(outputWord) + "\n").toString());
                    MainDisplay.setMainTextArea(output.append("Current guesses so far: " + lettersGuessed + "\n").toString());

                    //timer
                    try {
                        for (int timer = 5; timer > 0; timer--) {
                            Thread.sleep(1000);
                            MainDisplay.setMainTextArea(output.append("\nTimer expires in: " + timer + "\n").toString());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    output.setLength(0);

                    if(tries > 10){
                        return false;
                    }
                }
                catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please enter a letter");
                    MainDisplay.setMainTextArea("Please enter a letter");
                }
            }
        return true;
    }

}
