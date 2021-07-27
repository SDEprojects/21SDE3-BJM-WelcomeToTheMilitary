package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.Main;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gamecontroller.Interactions;
import com.welcomeToTheMilitary.gui.MainDisplay;

import java.util.Random;
import java.util.Scanner;

public class RPC implements iMinigame{

    String[] moves = {"Rock","Paper","Scissors"};

    @Override
    public boolean play() throws InterruptedException {

        int x = 1;

        StringBuilder output = new StringBuilder();

        clearConsole();

        boolean isDraw = false;
        boolean resultDraw =false;

        while(!isDraw){
            clearConsole();

            if(resultDraw){
                System.out.println("Last Game was a Draw!! play again\n" +
                        "_________________________________");
                MainDisplay.setMainTextArea(output.append("Last Game was a Draw!! play again\n").toString());
                resultDraw = false;
                        
            }else{
                System.out.println("Rock, Paper, Scissors\n" +
                        "---------------------");
                MainDisplay.setMainTextArea(output.append("Rock, Paper, Scissors\n").toString());
            }
            x =1;
            while( x< moves.length +1){
                System.out.println(x + ". " + moves[x-1]);
                x++;
            }

            System.out.println("Pick your Attack:\n(Type the number associated with Attack)\n" +
                    "----------------");
            MainDisplay.setMainTextArea(output.append("Type the number associated with Attack:\n1. Rock\n2. Paper \n3. Scissors\n").toString());

            while(MainDisplay.isStartButtonClicked()) {
                String pick = MainDisplay.getUserAction();
                try {
                    int pickInt = Integer.parseInt(pick);

                    if(pickInt > 0 && pickInt < moves.length +1){
                        int compMove = new Random().nextInt(moves.length);
                        pickInt--;
                        String resultstr = getWinner(pickInt,compMove);

                        if(resultstr.equals("win") || resultstr.equals("loss")){

                            return !resultstr.equals("loss");
                        }else{
                            resultDraw = true;
                        }
                    }
                } catch (NumberFormatException | InterruptedException ignored) {

                }
            }
            Thread.sleep(2000);
            output.setLength(0);
        }
        return false;
    }

    private String getWinner(int user, int comp) throws InterruptedException {

        StringBuilder output = new StringBuilder();

        System.out.println("You Picked "+ moves[user]+"\nThe Soldier Picked " + moves[comp]);
        MainDisplay.setMainTextArea(output.append("You Picked "+ moves[user]+"\nThe Soldier Picked " + moves[comp] + "\n").toString());

        if (user == 0) {
            //condiotional for comp moves
            if(comp == 0) {
                output.setLength(0);
                return "draw";
            }
            else if (comp ==1){
                System.out.println("Paper covers Rock");
                MainDisplay.setMainTextArea(output.append("Paper covers Rock").toString());

                Thread.sleep(3000);
                output.setLength(0);
            return "loss";
            }else if(comp ==2){
                System.out.println("Rock crushes Scissors");
                MainDisplay.setMainTextArea(output.append("Rock crushes Scissors").toString());
                Thread.sleep(3000);
                output.setLength(0);
                return "win";
            }
        }else if(user == 1){
            //    paper
            if(comp == 0) {
                System.out.println("Paper covers Rock");
                MainDisplay.setMainTextArea(output.append("Paper covers Rock").toString());
                Thread.sleep(3000);
                output.setLength(0);
                return "win";
            }
            else if (comp ==1){
                return "draw";
            }else if(comp ==2){
                System.out.println("Scissors cuts Paper");
                MainDisplay.setMainTextArea(output.append("Scissors cuts Paper").toString());
                Thread.sleep(3000);
                output.setLength(0);
                return "loss";
            }
        }else if(user == 2) {
            //    paper
            if (comp == 0) {
                System.out.println("Rock crushes Scissors");
                MainDisplay.setMainTextArea(output.append("Rock crushes Scissors").toString());
                Thread.sleep(3000);
                output.setLength(0);
                return "loss";
            } else if (comp == 1) {
                System.out.println("Scissors cuts Paper");
                MainDisplay.setMainTextArea(output.append("Scissors cuts Paper").toString());
                Thread.sleep(3000);
                output.setLength(0);
                return "win";
            } else if (comp == 2) {
                return "draw";
            }
        }
        return "";
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // placeholder for now, will figure it out
    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }

}