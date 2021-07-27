package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.gamecontroller.Display;
import com.welcomeToTheMilitary.gamecontroller.HelpmeHelper;
import com.welcomeToTheMilitary.gamecontroller.Interactions;
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

    // for user input / action
    private static Scanner userAction = new Scanner(System.in);

    public FinalBossFight() throws IOException, ParseException {
        clearScreen();
        introduction();
    }

    private void introduction() {
        System.out.println("=".repeat(5) + " Final Stage " + "=".repeat(5));
        System.out.println("You finally met the boss");
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
            displayBothStatus(usr, tempBoss);
            System.out.println("Number of Special Remained: " + usr.getNumberOfUserSpecialOnEachFinalBoss());
            System.out.print("What is your move?\n> ");
            userCommand = userAction.nextLine();
            if (userCommand == null || userCommand.length() == 0) {
                System.out.println("Boss: This is your final mercy!!!");
                System.out.println("type 'attack' | ");
                userCommand = userAction.nextLine();
            }
            userFightBossAction(userCommand, usr, tempBoss);
            if (userCommand.equals("attack") || userCommand.equals("special")) {
                bossFightUserRandomAction(usr, tempBoss);
            } else {
                System.out.println(tempBoss.getName() + " " + tempBoss.getName() + " is looking at you menacingly!");
            }
            //Service member using items in battle
           if(userCommand.equals("items")){
               System.out.println("What item do you want to use? " + usr.getItems());
               //user prompt on iem wanted to use
               String battleItem = userAction.nextLine();
               usr.useItem(battleItem);
            }
        }
        if (usr.getHealth() <= 0) {
            System.out.println("You lose");
            return false;
        } else if (tempBoss.getVitality() <= 0) {
            System.out.println("You won against final boss in Fort Sill");
            return true;
        } else {
            System.out.println("You tie");
            return false;
        }
    }

    private void userFightBossAction(String _userCommand, ServiceMember usr, Boss boss) throws InterruptedException {
        switch (_userCommand) {
            case "attack":
                int userHitDamage = usr.attack();
                System.out.println("attacking the boss");
                boss.subtractVitality(userHitDamage);
                System.out.println("You damaged SFC Daniels for "+ userHitDamage);
                return;
            case "use item":
                utilizeItem(usr);
                return;
            case "inventory":
                System.out.println("What item would you like to use: ");
                System.out.println(usr.getItems().toString());
                return;
            case "special":
                int userSpecialDamage = (usr.useSpecial());
                boss.subtractVitality(userSpecialDamage);
                System.out.println("You damaged SFC Daniels for "+ userSpecialDamage);
                return;
            default:
                System.out.println("=".repeat(10) + " OOPS Invalid Command" + "=".repeat(10));
                System.out.println("Commands Combat Fighting Mini Game Support");
                System.out.println("To attack: [attack]");
                System.out.println("To use item: [use item] -> type item name when it ask");
                System.out.println("To view inventory: [inventory]");
                System.out.println("To use special: [special]");
                return;
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

    // not implemented yet
    private void displayBothStatus(ServiceMember usr, Boss boss) {
        // Set up the String
        String introTitle = "=".repeat(14) + " Status Report " + "=".repeat(14);
        String playerTitle = "Player";
        String playerFinalBoss = "Final Boss";
        String playerRank = usr.getRank().getAbbreviation();
        String bossRank = boss.getRank();
        String playerName = usr.getName();
        String bossTitle = boss.getName();
        String playerHealth = "Hp: " + usr.getHealth();
        String bossHealth = "Hp: " + boss.getVitality();
        System.out.println("the hp of the player is " + usr.getHealth());
        System.out.println(introTitle);
        System.out.printf("%-20s %20s %n", playerTitle, playerFinalBoss);
        System.out.printf("%-20s %20s %n", playerName, bossTitle);
        System.out.printf("%-20s %20s %n", playerRank, bossRank);
        System.out.printf("%-20s %20s %n", playerHealth, bossHealth);
        System.out.println("=".repeat(22)  + "=".repeat(22));
    }
    
}

