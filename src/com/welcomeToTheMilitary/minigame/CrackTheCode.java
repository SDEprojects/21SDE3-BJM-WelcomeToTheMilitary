package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.character.ServiceMember;

public class CrackTheCode implements iMinigame{

    //test main
    public static void main(String[] args) {
        CrackTheCode test = new CrackTheCode();
        test.playMethod();
    }

    @Override
    public boolean play() {
        return false;
    }

    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }

    private String[] codes = {"password", "codes", "words", "secret"};


    public void playMethod(){
        int randomIndex = (int) Math.random() * codes.length;
        String randomWord = codes[randomIndex];

        System.out.println(randomWord);
    }

}
