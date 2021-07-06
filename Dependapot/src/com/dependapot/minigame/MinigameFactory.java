package com.dependapot.minigame;

public class MinigameFactory {
    public iMinigame playGame(String gameType) {
        if (gameType == null) {
            // return null for now
            return null;
        } else if (gameType.equals("rock paper scissors")) {
            return new RPC();
        } else if (gameType.equals("memorization game")) {
            return new DDRKeyboard();
        }
        return null;
    }
}
