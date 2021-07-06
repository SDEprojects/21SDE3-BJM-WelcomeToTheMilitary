package com.dependapot.json_pack;

import com.dependapot.character.Dependa;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonWriter implements iDataToRead {


    @Override
    public void dataToPass(Object _dataToPass) {//Creating a JSONObject object

        Dependa myObj = (Dependa)_dataToPass;
        Dependa soldierObject = (Dependa)_dataToPass;
        JSONObject jsonObject = new JSONObject();



        JSONArray jArray = new JSONArray();
        jsonObject.put("name",soldierObject.getName());
        jsonObject.put("special",soldierObject.getSpecial());
        jsonObject.put("location",soldierObject.getLocation());

        JSONObject soldierjobj = new JSONObject();
        soldierjobj.put("soldier", jsonObject);

        jArray.add(soldierjobj);

        try {
            FileWriter file = new FileWriter("jsonFiles/output.json");
            file.write(jArray.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);

    }



}


class testMain {




    public static void main(String[] args) {
//        Object testDependa = new Dependa("Jerad", "Dog Tags", "Fort Sill");
//        JsonWriter writer = new JsonWriter();
//        writer.dataToPass(testDependa);



        JsonReader jReader = new JsonReader();

        Dependa readDependa = jReader.returnSolder();

        System.out.println("This was read" + readDependa.getLocation() + "Name: " + readDependa.getName() + "special: " + readDependa.getSpecial());

    }


}