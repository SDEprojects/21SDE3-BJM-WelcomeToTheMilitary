package com.welcomeToTheMilitary.json_pack;

import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.ServiceMember;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {


    //test main
    public static void main(String[] args) throws IOException, ParseException {
        JsonReader jR = new JsonReader();
        //jR.getLocations();
        jR.getSoldiers();
    }


    private Reader reader = null;
    private InputStream inputFileOutputJSON = JsonReader.class.getResourceAsStream("/output.json");
    private InputStream inputFileSpecialJSON = JsonReader.class.getResourceAsStream("/specials.json");
    private InputStream inputFileLocationsJSON = JsonReader.class.getResourceAsStream("/locations.json");
    private InputStream inputFileItemsJSON = JsonReader.class.getResourceAsStream("/item.json");


    public static ArrayList<Item> getItems() throws IOException, ParseException {

        ArrayList<Item> myItems = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        String jsonItem = "jsonFiles/item.json";
        String itemContents = new String((Files.readAllBytes(Paths.get(jsonItem))));
        JSONObject obj = (JSONObject) jsonParser.parse(itemContents);

        //iterate though the base items for each base
        obj.keySet().forEach(base ->{
            JSONObject j = (JSONObject) obj.get(base);

            //iterate through the individual items for that particular base
            j.keySet().forEach(baseItems ->{
                JSONObject items = (JSONObject) j.get(baseItems);
                myItems.add(new Item(items.get("name").toString()));
            });
        });
        return myItems;
    }

    public static ArrayList<String> getLocations() throws IOException, ParseException {
        ArrayList<String> locations = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        String jsonLocations = "jsonFiles/locations.json";
        String locationContents = new String((Files.readAllBytes(Paths.get(jsonLocations))));
        JSONObject obj = (JSONObject) jsonParser.parse(locationContents);

        obj.keySet().forEach(location -> {
            locations.add(location.toString());
        });

        return locations;

    }

    public static HashMap<String, ArrayList<Enlisted>> getSoldiers() throws IOException, ParseException {
        //Hashmap will hold all soldiers, key = base name, value = arraylist of Enlisted objects
        HashMap<String, ArrayList<Enlisted>> soldiersList = new HashMap<>();

        //Load our JSON object from file.
        JSONParser jsonParser = new JSONParser();
        String jsonItem = "jsonFiles/soldiers.json";
        String itemContents = new String((Files.readAllBytes(Paths.get(jsonItem))));
        JSONObject obj = (JSONObject) jsonParser.parse(itemContents);

        //iterate though the base items for each base
        obj.keySet().forEach(base ->{
            //create a new K-V Pair for each base & Empty Array List
            soldiersList.put(base.toString(),new ArrayList<Enlisted>());


            JSONObject j = (JSONObject) obj.get(base);
            //System.out.println(base);
            //iterate through the individual soldiers for that particular base
            j.keySet().forEach(baseSoldiers ->{
                JSONObject soldier = (JSONObject) j.get(baseSoldiers);

                //Creates a new Enlisted object from json objects and adds to SoldiersList based on base name
                soldiersList.get(base).add(new Enlisted(soldier.get("name").toString(),soldier.get("attribute").toString(),soldier.get("rank").toString(),soldier.get("location").toString()));

            });
        });

        return soldiersList;
    }

    public ServiceMember returnSolder(){

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ServiceMember soldierToReturn = null;

//        String path = JsonReader.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//        System.out.println("You are about to enter the JSon files" + path);
        // try (FileReader reader = new FileReader("jsonFiles/output.json"))
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFileOutputJSON)))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
//            employeeList.forEach( emp -> parseSoldierObject( (JSONObject) emp ) );
//
            soldierToReturn = parseSoldierObject((JSONObject) employeeList.get(0));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return soldierToReturn;
    }

    public HashMap<String,String> getSpecials(){
        HashMap<String,String> specialHash = new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        // try (FileReader reader = new FileReader( "jsonFiles/specials.json"))
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFileSpecialJSON)))
        {
            //Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            //Iterate over employee array
            obj.keySet().forEach( eachSpecial -> {
                JSONObject special = (JSONObject) obj.get(eachSpecial);
                specialHash.put(special.get("name").toString(),special.get("description").toString());
            } );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return specialHash;
    }

    public ArrayList<String> getbuilStrings(String postname){
        ArrayList<String> buildingsList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        // try (FileReader reader = new FileReader( "jsonFiles/specials.json"))
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFileLocationsJSON)))
        {
            //Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            //Iterate over employee array
            obj.keySet().forEach( eachLocation -> {

                if(postname.equals("Fort Bliss") && eachLocation.toString().equals("Fort Bliss")){
                    System.out.println(eachLocation.toString());
                    JSONObject location = (JSONObject) obj.get(eachLocation);
                    JSONArray buildArray = (JSONArray) location.get("buildings") ;

                    for(int i = 0; i< buildArray.size();i++){
                        buildingsList.add(buildArray.get(i).toString());
                    }
                }
                else if (postname.equals("Fort Sill") && eachLocation.toString().equals("Fort Sill")){
                    System.out.println(eachLocation.toString());
                    JSONObject location = (JSONObject) obj.get(eachLocation);
                    JSONArray buildArray = (JSONArray) location.get("buildings") ;

                    for(int i = 0; i< buildArray.size();i++){
                        buildingsList.add(buildArray.get(i).toString());
                    }
                }else if (postname.equals("Fort Drum") && eachLocation.toString().equals("Fort Drum")){
                    System.out.println(eachLocation.toString());
                    JSONObject location = (JSONObject) obj.get(eachLocation);

                    JSONArray buildArray = (JSONArray) location.get("buildings") ;

                    for(int i = 0; i< buildArray.size();i++){
                        buildingsList.add(buildArray.get(i).toString());
                    }
                    System.out.println("dsafas");
                     // buildingsList = (ArrayList<String>) buildings;
                }

            } );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return buildingsList;
    }

    public static void printHelpRequestDataFromJSON() {
        JSONParser parser = new JSONParser();
        InputStream inputFilePossibleVerbAndNounJSON = JsonReader.class.getResourceAsStream("/possibleVerbAndNoun.json");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFilePossibleVerbAndNounJSON, "UTF-8")))
        {
            Object helpObjectObj = parser.parse(reader);
            JSONObject helpObject = (JSONObject) helpObjectObj;
            // helpObject = (JSONObject) parser.parse(new FileReader("jsonFiles/possibleVerbandNoun.json"));
            helpObject.keySet().forEach(eachInstruction -> {
                System.out.println("=".repeat(5) + " " + eachInstruction + " " + "=".repeat(5));
                JSONObject instructionSet = (JSONObject) helpObject.get(eachInstruction);
                System.out.println("Supported " + eachInstruction + " verb " + instructionSet.get("verb").toString());
                System.out.println("Supported " + eachInstruction + " noun " + instructionSet.get("noun").toString());
                System.out.println("Example " + eachInstruction + " example " + instructionSet.get("example").toString() + "\n");
            });
        } catch (IOException | ParseException e) {
            e.printStackTrace();;
        }
    }

    private ServiceMember parseSoldierObject(JSONObject soldierObject) throws IOException, ParseException {
//Get employee object within list
        JSONObject soldierobj = (JSONObject) soldierObject.get("soldier");

        //Get employee first name
        String name = (String) soldierobj.get("name");
        System.out.println(name);

        //Get employee last name
        String special = (String) soldierobj.get("special");
        System.out.println(special);

        //Get employee website name
        String location = (String) soldierobj.get("location");
        System.out.println(location);


        return new ServiceMember(name,special,location);
    }
}
