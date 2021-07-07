package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.attributes.Weapons;
import com.welcomeToTheMilitary.character.FinalBoss;
import com.welcomeToTheMilitary.character.ServiceMember;

import java.util.Scanner;

public class FinalBossFight implements iMinigame {
    private ServiceMember player = null;
    private FinalBoss boss = null;
    // for user input / action
    private static Scanner userAction = new Scanner(System.in);

    public FinalBossFight() {
        introduction();
    }

    private void introduction() {
        System.out.println("=".repeat(5) + " Final Stage " + "=".repeat(5));
        System.out.println("You finally met the boss");
    }

    @Override
    public boolean play() {
        return false;
    }

    @Override
    public boolean play(ServiceMember usr, FinalBoss boss) {
        String userCommand = "";
        while (usr.getHealth() != 0 && boss.getVitality() != 0) {
            // fight until someone die
            System.out.println("What is your move?\n> ");
            userCommand = userAction.nextLine();
            userFightBossAction(userCommand, usr, boss);
            BossFightUserRandomAction(usr, boss);
        }
        return false;
    }

    private void userFightBossAction(String _userCommand, ServiceMember usr, FinalBoss boss) {
        switch (_userCommand) {
            case "attack":
                int userHitDamage = usr.attack();
                System.out.println("attacking the boss");
                boss.setVitality(userHitDamage);
                return;
            case "use item":
                System.out.println("Trying to use item but not implemented yet! hahahah");
                // need to think
                return;
            default:
                System.out.println("??");
                return;
        }
    }

    private void BossFightUserRandomAction(ServiceMember usr, FinalBoss boss) {
        String bossActionList[] = {"atatck", "use spell"};
        int randomIndex = (int) (Math.random() * (bossActionList.length - 0));
        switch (bossActionList[randomIndex]) {
            case "attack":
                int bossHitDamage = boss.attack();
                usr.setHealth(usr.getHealth() - bossHitDamage);
                return;
            default:
                System.out.println(bossActionList[randomIndex] + " is not implemented yet");
                return;
        }
    }

    public static void main(String[] args) {
        ServiceMember park = new ServiceMember("Park", "a", "fort sill");
        FinalBoss ssg = new FinalBoss("SFC", "Daniels", 20,30,new Weapons("Fists",5,5,5));
        FinalBossFight fightBoss = new FinalBossFight();
        fightBoss.play(park, ssg);
    }
}

