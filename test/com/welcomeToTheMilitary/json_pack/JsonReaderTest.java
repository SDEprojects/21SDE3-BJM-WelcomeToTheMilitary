package com.welcomeToTheMilitary.json_pack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JsonReaderTest {

    private JSONParser jsonParser = new JSONParser();
    private String json = null;
    private String contents = null;
    private JSONObject obj = null;

    @Test
    public void getItemsPositive() throws IOException, ParseException {
        json = "jsonFiles/item.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        //because of how the json file is written, I had to get the key of base
        //and its corresponding number
        JSONObject fortSillObjects = (JSONObject) obj.get("Fort Sill");
        JSONObject blueberryMuffinObject = (JSONObject) fortSillObjects.get("1");
        String blueBerryMuffinExpected = "blueberry_muffins";
        String blueBerryMuffinActual = blueberryMuffinObject.get("name").toString();

        assertEquals(blueBerryMuffinExpected, blueBerryMuffinActual);
    }

    @Test
    public void getItemsNegativeNull() throws IOException, ParseException {
        json = "jsonFiles/item.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        //because of how the json file is written, I had to get the key of base
        //and its corresponding number
        JSONObject fortSillObjects = (JSONObject) obj.get("Fort Sill");
        JSONObject nonExistingObject = (JSONObject) fortSillObjects.get("nonExistingObject");

        assertNull(nonExistingObject);
    }

    @Test
    public void getLocationsPositive() throws IOException, ParseException {
        json = "jsonFiles/locations.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        JSONObject fortBlissObject = (JSONObject) obj.get("Fort Bliss");
        Object[] expectedBuildings = {"housing", "starbucks", "theater", "gym" , "mall", "pizza"};
        JSONArray actualBuildingsJson = (JSONArray) fortBlissObject.get("buildings");
        Object[] actualBuildings = actualBuildingsJson.toArray();

        assertEquals(expectedBuildings, actualBuildings);
    }

    @Test
    public void getLocationsNegativeNull() throws IOException, ParseException {
        json = "jsonFiles/locations.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        JSONObject fortBlissObject = (JSONObject) obj.get("nowhere");

        assertNull(fortBlissObject);
    }

    @Test
    public void getSpecialsPositive() throws IOException, ParseException {
        json = "jsonFiles/specials.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        JSONObject dogTagsObject = (JSONObject) obj.get("Dog Tags");
        String expectedSpecialName = "Dog Tags";
        String actualSpecialName = dogTagsObject.get("name").toString();

        assertEquals(expectedSpecialName, actualSpecialName);
    }

    @Test
    public void getSpecialsNegativeNull() throws IOException, ParseException {
        json = "jsonFiles/specials.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        JSONObject dogTagsObject = (JSONObject) obj.get("NonExistingSpecial");

        assertNull(dogTagsObject);
    }

    @Test
    public void getBuildingStringsPositive() throws IOException, ParseException {
        json = "jsonFiles/locations.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);

        ArrayList<String> arrayListExpected = new ArrayList<>();

        arrayListExpected.add("dfac");
        arrayListExpected.add("px");
        arrayListExpected.add("market");
        arrayListExpected.add("church");
        arrayListExpected.add("gym");
        arrayListExpected.add("barracks");

        JSONObject fortSillBuildingArrayJson = (JSONObject) obj.get("Fort Sill");
        JSONArray fortSillBuildingActual = (JSONArray) fortSillBuildingArrayJson.get("buildings");

        assertEquals(arrayListExpected, fortSillBuildingActual);
    }

    @Test
    public void getBuildingStringsNegative() throws IOException, ParseException {
        json = "jsonFiles/locations.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);

        ArrayList<String> arrayListExpected = new ArrayList<>();

        arrayListExpected.add("dfac");
        arrayListExpected.add("px");
        arrayListExpected.add("market");
        arrayListExpected.add("church");
        arrayListExpected.add("gym");
        arrayListExpected.add("barracks");
        arrayListExpected.add("no such building");

        JSONObject fortSillBuildingArrayJson = (JSONObject) obj.get("Fort Drum");
        JSONArray fortDrumBuildingActual = (JSONArray) fortSillBuildingArrayJson.get("buildings");

        assertNotEquals(arrayListExpected, fortDrumBuildingActual);
    }

    @Test
    public void getBuildingStringsNegativeNull() throws IOException, ParseException {
        json = "jsonFiles/locations.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);

        JSONObject fortSillBuildingArrayJson = (JSONObject) obj.get("Fort Drum");
        JSONArray fortDrumBuildingActual = (JSONArray) fortSillBuildingArrayJson.get("no such buildings");

        assertNull(fortDrumBuildingActual);
    }

    @Test
    public void getSoldiersPositiveInFortSill() throws IOException, ParseException {
        json = "jsonFiles/soldiers.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        JSONObject fortSillSoldiersObject = (JSONObject) obj.get("Fort Sill");

        //by how the json file is written, the key 9 has the value of these:
        JSONObject fortSillSoldiierLaginus = (JSONObject) fortSillSoldiersObject.get("9");

        String expectedSoldierName = "Laginus";
        String expectedSoldierRank = "Rank.E1";
        String expectedSoldierLocation = "barracks";
        String expectedAttribute = "Got a DUI last week and avoids extra duty.";

        String actualSoldierName = fortSillSoldiierLaginus.get("name").toString();
        String actualSoldierRank = fortSillSoldiierLaginus.get("rank").toString();
        String actualSoldierLocation = fortSillSoldiierLaginus.get("location").toString();
        String actualSoldierAttribute = fortSillSoldiierLaginus.get("attribute").toString();

        assertEquals(expectedSoldierName, actualSoldierName);
        assertEquals(expectedSoldierRank, actualSoldierRank);
        assertEquals(expectedSoldierLocation, actualSoldierLocation);
        assertEquals(expectedAttribute, actualSoldierAttribute);
    }

    @Test
    public void getSoldiersPositiveInFortBliss() throws IOException, ParseException {
        json = "jsonFiles/soldiers.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        JSONObject fortSillSoldiersObject = (JSONObject) obj.get("Fort Bliss");

        //by how the json file is written, the key 2 has the value of these:
        JSONObject fortSillSoldierRoger = (JSONObject) fortSillSoldiersObject.get("2");

        String expectedSoldierName = "Roger";
        String expectedSoldierRank = "Rank.E6";
        String expectedSoldierLocation = "starbucks";
        String expectedAttribute = "Deployed for one month and was sent back for a injury sustained at the gym";

        String actualSoldierName = fortSillSoldierRoger.get("name").toString();
        String actualSoldierRank = fortSillSoldierRoger.get("rank").toString();
        String actualSoldierLocation = fortSillSoldierRoger.get("location").toString();
        String actualSoldierAttribute = fortSillSoldierRoger.get("attribute").toString();

        assertEquals(expectedSoldierName, actualSoldierName);
        assertEquals(expectedSoldierRank, actualSoldierRank);
        assertEquals(expectedSoldierLocation, actualSoldierLocation);
        assertEquals(expectedAttribute, actualSoldierAttribute);
    }

    @Test
    public void getSoldiersNegativeInFortSillAndFortBlissNull() throws IOException, ParseException {
        json = "jsonFiles/soldiers.json";
        contents = new String((Files.readAllBytes(Paths.get(json))));
        obj = (JSONObject) jsonParser.parse(contents);
        JSONObject fortSillSoldiersObject = (JSONObject) obj.get("Fort Sill");
        JSONObject fortBlissSoldiersObject = (JSONObject) obj.get("Fort Bliss");

        //there are only 13 soldiers in Fort Sill in the json file. should return null if I get 14
        JSONObject fortSillSoldierNonExistingFortSill = (JSONObject) fortSillSoldiersObject.get("14");

        //there are only 12 soldiers in Fort Sill in the json file. should return null if I get 13
        JSONObject fortSillSoldierNonExistingFortBliss = (JSONObject) fortBlissSoldiersObject.get("13");

        assertNull(fortSillSoldierNonExistingFortSill);
        assertNull(fortSillSoldierNonExistingFortBliss);
    }
}