package com.welcomeToTheMilitary.minigame;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MinigameFactory {

    public iMinigame playGame() throws IOException, ParseException, InterruptedException {

        //Adds all our minigames to ArrayList and grabs one at random
        ArrayList<String> gameList = new ArrayList<>();
        gameList.add("rock paper scissors");
        gameList.add("memorization game");
        gameList.add("blitz math");
        gameList.add("crack the code");

        final int max = gameList.size();
        Random randInt = new Random();
        int randIndex = randInt.nextInt(max);

        String game = gameList.get(randIndex);

        return playGame(game);

    }

    public iMinigame playGame(String game) throws IOException, ParseException, InterruptedException {

        //returns the random game
        if (game.equals("rock paper scissors")) {
            return new RPC();
        } else if (game.equals("memorization game")) {
            return new DDRKeyboard();
        } else if (game.equals("blitz math")) {
            return new BlitzMath();
        }else if (game.equals("crack the code")) {
            return new CrackTheCode();
        } else if (game.equals("boss game")) {
            return new FinalBossFight();
        } else {
            return null;
        }
    }
}
