package com.welcomeToTheMilitary.minigame;

import java.util.ArrayList;

public class MinigameFactory {

    public iMinigame playGame() {

        //Adds all our minigames to ArrayList and grabs one at random
        ArrayList<String> gameList = new ArrayList<>();
        gameList.add("rock paper scissors");
        gameList.add("memorization game");
        gameList.add("blitz math");
        gameList.add("crack the code");
        final int min = 0;
        final int max = gameList.size();
        int randomIndex = (int) (Math.random() * (max - min));
        String game = gameList.get(randomIndex);

        return playGame(game);

    }

    public iMinigame playGame(String game) {

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
