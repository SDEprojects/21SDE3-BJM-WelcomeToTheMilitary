package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gui.mainDisplay;

import java.util.Arrays;
import java.util.Scanner;

public class CrackTheCode implements iMinigame{

    //test main
    public static void main(String[] args) {
        CrackTheCode test = new CrackTheCode();

        System.out.println(test.playMe());
    }

    @Override
    public boolean play() {
        return playMe();
    }

    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }

    private String[] codes = {"passwords", "passwords", "passwords", "passwords"};


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

    public boolean playMe(){

        System.out.println("You have 10 tries to guess the word!");
        mainDisplay.setMainTextArea("");

        int tries = 0;

        String randomWord = getRandomWord();
        Scanner scanner = new Scanner(System.in);

        char[] lettersToGuess = randomWord.toCharArray();
        char[] outputWord = maskTheWord(randomWord).toCharArray();

        System.out.println(outputWord);

        while(!Arrays.equals(lettersToGuess, outputWord)){

            System.out.println("Enter a letter");
            try{
                //player's letter guess
                char userInput = scanner.nextLine().charAt(0);
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

                System.out.println("Current tries: " + tries);
                System.out.println(outputWord);
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("Please enter a letter");
            }
        }

        if(tries > 10){
            return false;
        }
        else{
            return true;
        }
    }

}
