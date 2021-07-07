//package com.dependapot.bases;
//
//import com.dependapot.character.ServiceMember;
//import com.dependapot.character.LowerEnlist;
//import com.dependapot.textparser.ParseResponse;
//import com.welcomeToTheMilitary.character.LowerEnlist;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Post {
//    // method to grab name of the post
//    public String getName() {
//        return name;
//    }
//
//    // method for enterToBuilding
//    public void enterToBuilding(String building) {
//        if (building == null || building.length() == 0) {
//            System.out.println("Enter To Building method: invalid");
//            return;
//        }
//        // need a method to get the solider from building
//        ArrayList<LowerEnlist> foundedSolider = getSolider(building);
//        System.out.println("You are entering the " + building);
//        System.out.println("You find " + foundedSolider.size() + " soldier's from another company");
//        System.out.println("You saw...");
//        for (LowerEnlist eachSoldier : foundedSolider) {
//            System.out.println("Name: " + eachSoldier.getName());
//            System.out.println("Attribute: " + eachSoldier.getAttribute());
//        }
//        return;
//    }
//
//}


