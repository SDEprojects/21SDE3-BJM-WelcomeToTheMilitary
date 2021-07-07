package com.welcomeToTheMilitary.json_pack;

import com.welcomeToTheMilitary.character.ServiceMember;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {


    public ServiceMember returnSolder(){

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ServiceMember soldierToReturn = null;

        try (FileReader reader = new FileReader("jsonFiles/output.json"))
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

    private ServiceMember parseSoldierObject(JSONObject soldierObject){
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
