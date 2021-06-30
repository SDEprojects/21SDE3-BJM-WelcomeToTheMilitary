package com.dependapot.minigame;

public class DDRKeyboard implements Minigame {
    private boolean isWin = false;

    public DDRKeyboard() {
        //
    }

    private void instruction() {
        System.out.println("=".repeat(20));
        System.out.println("Challenge");
    }

    @Override
    public boolean play() {
        return false;
    }
}
