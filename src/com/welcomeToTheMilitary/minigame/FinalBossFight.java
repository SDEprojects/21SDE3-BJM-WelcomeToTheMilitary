package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gamecontroller.Display;
import com.welcomeToTheMilitary.gamecontroller.HelpmeHelper;
import com.welcomeToTheMilitary.gamecontroller.Interactions;
import com.welcomeToTheMilitary.gui.MainDisplay;
import com.welcomeToTheMilitary.textparser.ParseResponse;
import com.welcomeToTheMilitary.textparser.TextParser;

// testing
import com.welcomeToTheMilitary.boss.Boss;
import org.json.simple.parser.ParseException;
// end of testing
import java.io.IOException;
import java.util.Scanner;

public class FinalBossFight implements iMinigame {
    private ServiceMember player = null;
    private Boss tempBoss = null;
    private static ParseResponse response = null;
    private static TextParser parser = null;
    private int specialCount = 0;

    private StringBuilder output = new StringBuilder();

    // for user input / action
    private static Scanner userAction = new Scanner(System.in);

    public FinalBossFight() throws IOException, ParseException, InterruptedException {
        clearScreen();
        introduction();
    }

    private void introduction() throws InterruptedException {
        System.out.println("=".repeat(5) + " Final Stage " + "=".repeat(5));
        System.out.println("You finally met the boss");
        MainDisplay.setMainTextArea(output.append("You finally met the boss\n").toString());
        Thread.sleep(1000);
    }

    // clear the screen
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public boolean play() {
        return false;
    }

    // it was finalBoss as param
    @Override
    public boolean play(ServiceMember usr) throws InterruptedException {

        // testing
        if (usr.getPostName().equals("Fort Sill")) {
            tempBoss = new Boss("SFC", "Daniels", 25,90, 10, 2);
        }
        if (usr.getPostName().equals("Fort Bliss")) {
            tempBoss = new Boss("CSM", "Fort Bliss Command Sergeant Major", 50,15, 70, 30);
        }
        // end of testing
        String userCommand = "";

        while (usr.getHealth() > 0 && tempBoss.getVitality() > 0) {

            MainDisplay.setUserAction("");
            userCommand = "";

            displayBothStatus(tempBoss);
            System.out.println("Number of Special Remained: " + usr.getNumberOfUserSpecialOnEachFinalBoss());
            //MainDisplay.setMainTextArea(output.append("Number of special(s) remaining: " + usr.getNumberOfUserSpecialOnEachFinalBoss()).toString());
            System.out.print("What is your move?\n");
            MainDisplay.setMainTextArea(output.append("What is your move?\n").toString());
            Thread.sleep(2000);

            while (userCommand.equals("")) {
                userCommand = MainDisplay.getUserAction();
                System.out.println(userCommand);
            }

            System.out.println(userCommand);
            if(!userCommand.equals("")) {
                userFightBossAction(userCommand, usr, tempBoss);
                if (userCommand.equals("attack") || userCommand.equals("special")) {
                    bossFightUserRandomAction(usr, tempBoss);
                } else {
                    System.out.println(tempBoss.getName() + " " + tempBoss.getName() + " is looking at you menacingly!");
                    MainDisplay.setMainTextArea(output.append(tempBoss.getName() + " " + tempBoss.getName() + " is looking at you menacingly!" + "\n").toString());
                }
                //Service member using items in battle
                if (userCommand.equals("items")) {
                    System.out.println("What item do you want to use? " + usr.getItems());
                    MainDisplay.setMainTextArea(output.append("What item do you want to use? " + usr.getItems()).toString());
                    String battleItem = MainDisplay.getUserAction();
                    Thread.sleep(3000);
                    usr.useItem(battleItem);
                }
                output.setLength(0);
            }
        }
        if (usr.getHealth() <= 0) {
            System.out.println("You lose");
            MainDisplay.setMainTextArea("You lose");
            return false;
        } else if (tempBoss.getVitality() <= 0) {
            System.out.println("You won against final boss in Fort Sill");
            MainDisplay.setMainTextArea("You won against final boss in Fort Sill");
            return true;
        } else {
            System.out.println("You tie");
            MainDisplay.setMainTextArea("It's a tie!");
            return false;
        }
    }

    private void userFightBossAction(String _userCommand, ServiceMember usr, Boss boss) throws InterruptedException {

        switch (_userCommand) {
            case "attack":
                int userHitDamage = usr.attack();
                System.out.println("attacking the boss");
                MainDisplay.setMainTextArea(output.append("attacking the boss!!\n").toString());
                boss.subtractVitality(userHitDamage);
                System.out.println("You damaged SFC Daniels for "+ userHitDamage);
                MainDisplay.setMainTextArea(output.append("You damaged SFC Daniels for "+ userHitDamage + "\n").toString());
                Thread.sleep(2000);
                break;
            case "use item":
                utilizeItem(usr);
                break;
            case "inventory":
                System.out.println("What item would you like to use: ");
                System.out.println(usr.getItems().toString());
                MainDisplay.setMainTextArea(output.append("What item would you like to use: " + usr.getItems()).toString());
                Thread.sleep(2000);
                break;
            case "special":
                if(specialCount <=3){
                    specialCount++;
                    int userSpecialDamage = (usr.useSpecial());
                    boss.subtractVitality(userSpecialDamage);
                    System.out.println("You damaged SFC Daniels for "+ userSpecialDamage);
                    MainDisplay.setMainTextArea(output.append("You damaged SFC Daniels for "+ userSpecialDamage).toString());
                    Thread.sleep(2000);
                }
                break;
            default:
                System.out.println("=".repeat(10) + " OOPS Invalid Command" + "=".repeat(10));
                System.out.println("Commands Combat Fighting Mini Game Support");
                System.out.println("To attack: [attack]");
                System.out.println("To use item: [use item] -> type item name when it ask");
                System.out.println("To view inventory: [inventory]");
                System.out.println("To use special: [special]");
                break;
        }
    }

    private void utilizeItem(ServiceMember usrSM) {
        Scanner inputItemName = new Scanner(System.in);
        System.out.println("Please type the item name\n>");
        String userSelectedItemInput = inputItemName.nextLine();

        Item itemToUse = null;

        for(Item item : usrSM.getItems()){
            if(item.getName().equals(userSelectedItemInput)){
                itemToUse = item;
            }
        }
        usrSM.useItem(itemToUse);
    }

    private void bossFightUserRandomAction(ServiceMember usr, Boss boss) {
        String bossActionList[] = {"attack"};
        int randomIndex = (int) (Math.random() * (bossActionList.length - 0));
        switch (bossActionList[randomIndex]) {
            case "attack":
                // temp
                // int bossHitDamage = boss.attack();
                int bossHitDamage = boss.attack();
                usr.setHealth(bossHitDamage, false);
                return;
            default:
                System.out.println(bossActionList[randomIndex] + " is not implemented yet");
                return;
        }
    }

    private void displayBothStatus(Boss boss) {
        // Set up the String
        String playerFinalBoss = "Final Boss";
        String bossRank = boss.getRank();
        String bossTitle = boss.getName();
        String bossHealth = "Hp: " + boss.getVitality();

        MainDisplay.setMapAreaText("Boss name: " + bossTitle + "\n" +
                bossRank + "\n" +  "boss health: " + bossHealth + "\n" + "Specials use: " + specialCount);
    }
    
}

