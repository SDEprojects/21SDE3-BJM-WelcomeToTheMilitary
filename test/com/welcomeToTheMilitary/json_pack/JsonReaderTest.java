package com.welcomeToTheMilitary.json_pack;

import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.bases.BaseMap;
import com.welcomeToTheMilitary.character.ServiceMember;
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

    private JsonReader jR = new JsonReader();

    private BaseMap fortSill = null;
    private BaseMap fortBliss = null;
    private Item item = null;
    private ServiceMember serviceMember = null;
    private ArrayList<Item> items;

    private JSONParser jsonParser = new JSONParser();
    private String jsonItem = null;
    private String itemContents = null;
    private JSONObject obj = null;

    public JsonReaderTest() throws IOException, ParseException {
    }

    @Test
    public void getItems() throws IOException, ParseException {
        jsonItem = "jsonFiles/item.json";
        itemContents = new String((Files.readAllBytes(Paths.get(jsonItem))));
        obj = (JSONObject) jsonParser.parse(itemContents);
        JSONObject fortSillObjects = (JSONObject) obj.get("Fort Sill");
        JSONObject blueberryMuffinObject = (JSONObject) fortSillObjects.get("1");
        String blueBerryMuffinExpected = "blueberry_muffins";
        String blueBerryMuffinActual = blueberryMuffinObject.get("name").toString();

        assertEquals(blueBerryMuffinExpected, blueBerryMuffinActual);
    }

    @Test
    public void getLocations() {
    }

    @Test
    public void getSoldiers() {
    }

    @Test
    public void returnSolder() {
    }

    @Test
    public void getSpecials() {
    }

    @Test
    public void getBuildingStrings() {
    }

    @Test
    public void printHelpRequestDataFromJSON() {
    }

}