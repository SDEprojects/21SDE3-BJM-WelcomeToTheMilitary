package com.welcomeToTheMilitary.gamecontroller;

import org.json.simple.parser.ParseException;

import java.io.*;


public class SaveAndLoad implements java.io.Serializable {

    //Top level object
    static GameController fullGame;

    public SaveAndLoad() throws IOException, ParseException {
    }

    public static void saveGame(){
        try{
            //create a new output object to save game
            FileOutputStream fos = new FileOutputStream("Game.txt");
            //opens stream object to write data so it can be reconstituted on reload
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(fullGame); //pass the top level game object
            //clears any elements that may be in the stream
            oos.flush();
            //closes stream
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
    public static void loadGame(){
        try{
            FileInputStream fis = new FileInputStream("Game.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            // gameController class and object
            fullGame = (GameController) ois.readObject();
            ois.close();
            System.out.println("Your last save point has been loaded\n");

        } catch (FileNotFoundException e) {
            System.out.println("File not found error has occured");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOE exception error has occured");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
