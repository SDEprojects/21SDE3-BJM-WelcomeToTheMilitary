//package com.dependapot.bases;
//
//import com.dependapot.character.Dependa;
//import com.dependapot.character.LowerEnlist;
//import com.dependapot.textparser.ParseResponse;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Post {
//    // put all created soldiers into facility
//    private void setUpFacility() {
//        this.buildings = new HashMap<>();
//        this.buildings.put("dfac", prepareSoldierInDfac());
//        this.buildings.put("px", prepareSoldierInPX());
//        this.buildings.put("commissary", prepareSoldierInCommissary());
//        this.buildings.put("church", prepareSoldierInChurch());
//        this.buildings.put("gym", prepareSoldierInGym());
//        this.buildings.put("barracks", prepareSoldierInBarracks());
//    }
//    // method for enterToBuilding
//    public void enterToBuilding(String building) {
//        if (building == null || building.length() == 0) {
//            System.out.println("EnterToBuilding method: invalid");
//            return;
//        }
//    // need a method to get the solider from building
//    ArrayList<LowerEnlist> foundedSolider = getSolider(building);
//        System.out.println("You are entering to the " + building);
//        System.out.println("You find " + foundedSolider.size() + " attractive soldiers");
//        System.out.println("You saw...");
//        for (LowerEnlist eachSoldier : foundedSolider) {
//        System.out.println("Name: " + eachSoldier.getName());
//        System.out.println("Attribute: " + eachSoldier.getAttribute());
//    }
//        return;
//}
//    public void enterBuildingController(ParseResponse response){
//        String noun = response.getNoun();
//        String verb = response.getVerb();
//        boolean is_Dependa_in_DFAC;
//        boolean is_Dependa_in_Barracks;
//        boolean is_Dependa_in_Church;
//        boolean is_Dependa_in_Gym;
//        boolean is_Dependa_in_PX;
//        boolean is_Dependa_in_Commisary;
//        System.out.println(noun);
//        currentDependaLocation = "Fort Sill";
////        need to pull from text parser
//        String _bldg_name;
////        do{
////            System.out.println("You are in Beautiful Fort Sill, Oklahoma. You are standing at the gate ready to explore what this base has to offer!!! ");
////        }while (building_Seen.contains(_bldg_name));
////        building_Seen.add(_bldg_name);
////        String building_description = null;
//        if (noun.equals("dfac")){
//            is_Dependa_in_DFAC = true;
//            is_Dependa_in_Barracks = false;
//            is_Dependa_in_Church = false;
//            is_Dependa_in_Gym = false;
//            is_Dependa_in_PX = false;
//            is_Dependa_in_Commisary = false;
//            // ding_description = "A buffet style Dinning Facility with powedered eggs, turkey bacon, and all the water you can have";
//            enterToBuilding("dfac");
//        }else if (noun.equals("px")){
//            is_Dependa_in_DFAC = false;
//            is_Dependa_in_Barracks = false;
//            is_Dependa_in_Church = false;
//            is_Dependa_in_Gym = false;
//            is_Dependa_in_PX = true;
//            is_Dependa_in_Commisary = false;
//            enterToBuilding("px");
//            // building_description = "You do not pay taxes here. Come purchase all your unneccessary needs!!!";
//        }else if (noun.equals("commissary")){
//            is_Dependa_in_DFAC = false;
//            is_Dependa_in_Barracks = false;
//            is_Dependa_in_Church = false;
//            is_Dependa_in_Gym = false;
//            is_Dependa_in_PX = false;
//            is_Dependa_in_Commisary = true;
//            enterToBuilding("commissary");
//            // building_description = "Tax free grocery with all the hot chips you can buy";
//        }else if(noun.equals("barracks")){
//            is_Dependa_in_DFAC = false;
//            is_Dependa_in_Barracks = true;
//            is_Dependa_in_Church = false;
//            is_Dependa_in_Gym = false;
//            is_Dependa_in_PX = false;
//            is_Dependa_in_Commisary = false;
//            enterToBuilding("barracks");
//            // building_description = "Standing still since 42', the mold in these barracks are older than your grandfather";
//        }else if(noun.equals("gym")){
//            is_Dependa_in_DFAC = false;
//            is_Dependa_in_Barracks = false;
//            is_Dependa_in_Church = false;
//            is_Dependa_in_Gym = true;
//            is_Dependa_in_PX = false;
//            is_Dependa_in_Commisary = false;
//            enterToBuilding("gym");
////            building_description = "You can sell LuLaRoe leggings here and only do lower body exercises.";
//        }else if(noun.equals("church")){
//            is_Dependa_in_DFAC = false;
//            is_Dependa_in_Barracks = false;
//            is_Dependa_in_Church = true;
//            is_Dependa_in_Gym = false;
//            is_Dependa_in_PX = false;
//            is_Dependa_in_Commisary = false;
//            enterToBuilding("church");
////            building_description = "You've been here before and you will be back. ";
//        }else{
//
//        }
////      return  enterBuildingController( ParseResponse);
////    return new Fort_Sill_Map(building_description,false);
//    }
//
//}
//
//
