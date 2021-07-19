package com.welcomeToTheMilitary.bases;

import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.Rank;
import com.welcomeToTheMilitary.textparser.ParseResponse;
import org.json.simple.JSONObject;

import java.util.*;

public class Fort_Sill_Map {
    private Random random_WO_Generator = new Random();
    public String name = "Fort Sill";
    private final String description;

    // private -> updated CODE WITH Jun
    private HashMap<String, ArrayList<Enlisted>> buildings = null;
    private String currentDependaLocation = null;
    private HashMap<String, String> itemBasedOnFacility = null;

    // TEST ITEM
    private HashMap<String, Item> testItemBasedOnFacility = null;
    private static GenerateItemHelper generateItemHelper = null;
    // End of TEST ITEM

//    Constructor
    public Fort_Sill_Map(String _name, String description){
        this.name = _name;
        this.description = description;
        generateItemHelper = new GenerateItemHelper();
        setUpFacility();
        testSetItems();
    }

    // test method
    public Item showItemInTheFacilityTest(String facility) {
        return testItemBasedOnFacility.get(facility);
    }

    // test setting up the item
    public void testSetItems() {
        this.testItemBasedOnFacility = new HashMap<>();
        try {
            // items in hashmap
            JSONObject itemsFromJSON  = generateItemHelper.getItemsFromJSONFile(this.getName());
            // System.out.println(itemsFromJSON);
            // TODO: iterate and add into the hashmap
            String[] keysArr = Arrays.copyOf(itemsFromJSON.keySet().toArray(), itemsFromJSON.keySet().toArray().length, String[].class);
            // loop through
            for (int i = 0; i < keysArr.length; i++) {
                JSONObject eachItem = (JSONObject) itemsFromJSON.get(keysArr[i]);
                String itemName = String.valueOf(eachItem.get("name"));
                String itemLocation = String.valueOf(eachItem.get("location"));
                String description = String.valueOf(eachItem.get("description"));
                String itemType = String.valueOf(eachItem.get("type"));
                int itemValue = 0;
                testItemBasedOnFacility.put(itemLocation, new Item(itemName, description, itemType, itemValue));
            }
        } catch (NullPointerException e) {
            System.out.println("Item is null: Fort Sill Map.class");
            e.printStackTrace();
        }
    }

    // getter for item associated with the facility
    // param: building name
    public String getItemFromFacility(String facilityName) {
        return itemBasedOnFacility.get(facilityName);
    }


    // method to grab name of the post
    public String getName() {
        return name;
    }

    // method for enterToBuilding
    public void enterToBuilding(String building) {
        if (building == null || building.length() == 0) {
            System.out.println("Enter To Building method: invalid");
            return;
        }
        // need a method to get the solider from building
        ArrayList<Enlisted> foundedSolider = getSolider(building);
        System.out.println("You are entering the " + building);
        System.out.println("You find " + foundedSolider.size() + " soldier's from another company");
        System.out.println("You saw...");
        for (Enlisted eachSoldier : foundedSolider) {
            System.out.println("Name: " + eachSoldier.getName() + " Rank: " + eachSoldier.getRank());
            System.out.println("Attribute: " + eachSoldier.getAttribute());
        }
    }

    //

    // going to get all the solider based on the building that is passed by parameter
    public ArrayList<Enlisted> getSolider(String building) {
        HashMap<String, ArrayList<Enlisted>> data = this.buildings;
        ArrayList<Enlisted> enlists = data.get(building);
        return enlists;
    }
    // get buildings for
    public ArrayList<String> getBuildings(){
        ArrayList<String> bldgs = new ArrayList<>();
        for (String eachBuilding: buildings.keySet()){
            bldgs.add(eachBuilding);
        }
        return bldgs;
    }

    // get solider's name
    public ArrayList<String> getSoldierNameBasedOnBuilding(String building) {
        // check if the user is currently at the gate
        if (building.equals("gate")) {
            System.out.println("You are currently at the gate.\nNo one to talk to...T_T");
            return new ArrayList<String>();
        }
        String targetBuilding = building.trim();
        ArrayList<String> nameOfSoldiersInBuilding = new ArrayList<>();
        ArrayList<Enlisted> soldiersInBuilding = buildings.get(targetBuilding);
        for (Enlisted eachSoldier : soldiersInBuilding) {
            nameOfSoldiersInBuilding.add(eachSoldier.getName());
        }
        return nameOfSoldiersInBuilding;
    }

    // put all created soldiers into facility
    private void setUpFacility() {
        this.buildings = new HashMap<>();
        this.buildings.put("dfac", prepareSoldierInDfac());
        this.buildings.put("px", prepareSoldierInPX());
        this.buildings.put("market", prepareSoldierInCommissary());
        this.buildings.put("church", prepareSoldierInChurch());
        this.buildings.put("gym", prepareSoldierInGym());
        this.buildings.put("barracks", prepareSoldierInBarracks());
    }

//    helper method to set random WO in facilities
//private ArrayList<WarrantOfficer> warrantsInBliss(){
//    ArrayList<WarrantOfficer> fortSill = new ArrayList<>();
//    WarrantOfficer WO1 = new WarrantOfficer("WO1", "Chet", "I just traded my 28% APR Camaro for a GeoMetro. Want to become a Warrant!!!");
//    WarrantOfficer WO2 = new WarrantOfficer("WO2", "David", "I don't wash my coffee cup. I also work with computers. Do you DND!!!");
//    WarrantOfficer WO3 = new WarrantOfficer("WO3", "Michael", "I just flew in from Alabama. Do you want to fly helicopters and planes?");
//    WarrantOfficer WO4 = new WarrantOfficer("WO4", "Phillip", "What year is it? Come with me if you want travel the world. Look at my green floppy hat!!!");
//    WarrantOfficer WO5 = new WarrantOfficer("WO5", "Garcia", "Who am I, you ask? I don't exist. I play golf during work hours. Come join the MI Branch where no one is held accountable!");
//    fortSill.add(WO1);
//    fortSill.add(WO2);
//    fortSill.add(WO3);
//    fortSill.add(WO4);
//    fortSill.add(WO5);
//    return fortSill;
//}
// pick a random WO


    // helper method to set up the place with correct soldiers for the dfac
    private ArrayList<Enlisted> prepareSoldierInDfac() {
        ArrayList<Enlisted> dfacSoldier = new ArrayList<>();
        Enlisted E1 = new Enlisted("Brad", "High and Thight. Forever skinny", Rank.E1);
        Enlisted E2 = new Enlisted("Jeremy", "Can dip a whole can of Skoal Wintergreen. Wears combat boots with his jeans", Rank.E2);
        Enlisted E3 = new Enlisted("Rogers", "Has an associates in Political Science. Wears Nine Line Apparel. Says Hooah a lot", Rank.E3);
        dfacSoldier.add(E1);
        dfacSoldier.add(E2);
        dfacSoldier.add(E3);
        return dfacSoldier;
    }
    // helper method to set up the place with correct soldiers for the PX
    private ArrayList<Enlisted> prepareSoldierInPX(){
        ArrayList<Enlisted> pxSoldier = new ArrayList<>();
        Enlisted E6 = new Enlisted("Shad", "Grumpy with gray hairs. Walks with a purpose.", Rank.E6);
//        WarrantOfficer WO1 = new WarrantOfficer()
        pxSoldier.add(E6);
// add Warrant Officer
        return pxSoldier;
    }
    // helper method to set up the place with correct soldiers for the Commissary
    private ArrayList<Enlisted> prepareSoldierInCommissary(){
        ArrayList<Enlisted> commSoldier = new ArrayList<>();
        Enlisted E6 = new Enlisted("Arturo", "Always having a cookout. Dances with products as he puts them into his cart.", Rank.E6);
        commSoldier.add(E6);
        //warrantsInBliss();
        return commSoldier;
    }

    // helper method to set up the place with correct soldiers for the church
    private ArrayList<Enlisted> prepareSoldierInChurch(){
        ArrayList<Enlisted> churchSoldier = new ArrayList<>();
        Enlisted E4 = new Enlisted("Mason", "Hide n Seek Champion from his unit. Barracks Lawyer.", Rank.E4);
        Enlisted E6 = new Enlisted("John", "Will pray with you and take you to choir practice.", Rank.E6);
        churchSoldier.add(E4);
        churchSoldier.add(E6);
        return churchSoldier;
    }

    // helper method to set up the place with correct soldiers for the gym
    private ArrayList<Enlisted> prepareSoldierInGym(){
        ArrayList<Enlisted> gymSoldier = new ArrayList<>();
        Enlisted E5 = new Enlisted("Brandon", "Do you even lift, Bro.", Rank.E5);
// CID
        gymSoldier.add(E5);
        return gymSoldier;
    }
    // helper method to set up the place with correct soldiers for the Barracks
    private ArrayList<Enlisted> prepareSoldierInBarracks(){
        ArrayList<Enlisted> barracksSoldier = new ArrayList<>();
        Enlisted E1 = new Enlisted("Laginus", "Got a DUI last week and avoids extra duty.", Rank.E1);
        Enlisted E2 = new Enlisted("Soko", "Pot Belly. Always dirty because he's a mechanic. Never fixes anything tho.", Rank.E2);
        Enlisted E3 = new Enlisted("David", "Just passed Ranger School. Lives off coffee and MRE's. Listens to Classic Rock.", Rank.E3);
        Enlisted E4 = new Enlisted("Stephen", "Has a low-fade. Always preparing for SFAS. Won't tell you what it means.", Rank.E4);
        barracksSoldier.add(E1);
        barracksSoldier.add(E2);
        barracksSoldier.add(E3);
        barracksSoldier.add(E4);
        return barracksSoldier;
    }
    // it used to be static
    public void enterBuildingController(ParseResponse response){
        String noun = response.getNoun();
        String verb = response.getVerb();
        boolean is_Dependa_in_DFAC;
        boolean is_Dependa_in_Barracks;
        boolean is_Dependa_in_Church;
        boolean is_Dependa_in_Gym;
        boolean is_Dependa_in_PX;
        boolean is_Dependa_in_Commisary;
        System.out.println(noun);
        currentDependaLocation = "Fort Sill";
//        need to pull from text parser
        String _bldg_name;
//        do{
//            System.out.println("You are in Beautiful Fort Sill, Oklahoma. You are standing at the gate ready to explore what this base has to offer!!! ");
//        }while (building_Seen.contains(_bldg_name));
//        building_Seen.add(_bldg_name);
//        String building_description = null;
        if (noun.equals("dfac")){
            is_Dependa_in_DFAC = true;
            is_Dependa_in_Barracks = false;
             is_Dependa_in_Church = false;
             is_Dependa_in_Gym = false;
             is_Dependa_in_PX = false;
             is_Dependa_in_Commisary = false;
//             building_description = "A buffet style Dinning Facility with powdered eggs, turkey bacon, and all the water you can have";
            enterToBuilding("dfac");
        }else if (noun.equals("px")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = false;
            is_Dependa_in_Church = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_PX = true;
            is_Dependa_in_Commisary = false;
            enterToBuilding("px");
            // building_description = "You do not pay taxes here. Come purchase all your unnecessary needs!!!";
        }else if (noun.equals("market")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = false;
            is_Dependa_in_Church = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_PX = false;
            is_Dependa_in_Commisary = true;
            enterToBuilding("market");
            // building_description = "Tax free grocery with all the hot chips you can buy";
        }else if(noun.equals("barracks")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = true;
            is_Dependa_in_Church = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_PX = false;
            is_Dependa_in_Commisary = false;
            enterToBuilding("barracks");
            // building_description = "Standing still since 42', the mold in these barracks are older than your grandfather";
        }else if(noun.equals("gym")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = false;
            is_Dependa_in_Church = false;
            is_Dependa_in_Gym = true;
            is_Dependa_in_PX = false;
            is_Dependa_in_Commisary = false;
            enterToBuilding("gym");
//            building_description = "You can find rusted gym equipment and listen to 80's rock.";
        }else if(noun.equals("church")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = false;
            is_Dependa_in_Church = true;
            is_Dependa_in_Gym = false;
            is_Dependa_in_PX = false;
            is_Dependa_in_Commisary = false;
            enterToBuilding("church");
//            building_description = "You've been here before and you will be back. ";
        }else{

        }
//      return  enterBuildingController( ParseResponse);
//    return new Fort_Sill_Map(building_description,false);
    }
}
