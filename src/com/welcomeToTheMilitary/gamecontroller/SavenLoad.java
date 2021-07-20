package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.gamecontroller.GameController;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SavenLoad {
    public static void saveGame(){
        try{
            FileOutputStream fos = new FileOutputStream("Game.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(null);
            oos.flush(); //write out any buffered bytes
            oos.close();
            System.out.println("Game has been saved\n");
        } catch (FileNotFoundException e) {
            System.out.println("And error has occured and was not able to save the game");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("And error has occured and was not able to save the game");
            e.printStackTrace();
        }
    }
}
