package com.welcomeToTheMilitary.textparser;

import com.welcomeToTheMilitary.bases.GenerateItemHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TextParserHelper {
    private HashMap<String, String> verbAndNoun = null;
    private GenerateItemHelper generateItemHelper = null;

    // constructor
    public TextParserHelper() {
        generateItemHelper = new GenerateItemHelper();
        verbAndNoun = new HashMap<>();
    }

    public HashMap<String, String> getHashMapData() {
        return this.verbAndNoun;
    }

    public String getVerbFromHelper() {
        return this.verbAndNoun.get("verb");
    }

    public String getNounFromHelper() {
        return this.verbAndNoun.get("noun");
    }

    public boolean getCoreInstructionHelper(String userActionVerb, String userActionNoun, String postType) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        String jsonInstructions = "jsonFiles/verbAndNounForParse.json";
        String instructionContents = new String((Files.readAllBytes((Paths.get(jsonInstructions)))));

            JSONObject helpObject = (JSONObject) jsonParser.parse(instructionContents);
            JSONObject fortSillData = (JSONObject) helpObject.get(postType);
//            System.out.println("TextParser Data: " + fortSillData);
            JSONObject matchingAction = (JSONObject) fortSillData.get(userActionVerb);
            if (matchingAction != null) {
                // found matching key
                // do something

                String isValidNoun = checkUserEnterNounValid(matchingAction, userActionNoun);
                if (isValidNoun != null) {
                    verbAndNoun.put("verb", userActionVerb);
                    verbAndNoun.put("noun", isValidNoun);
                    return true;
                }
            } else {
                // check all synonym
                String possibleMatchedSynonym = null;
                String possibleMatchedKey = null;
                String[] keysArr = Arrays.copyOf(fortSillData.keySet().toArray(), fortSillData.keySet().toArray().length, String[].class);
                // System.out.println(Arrays.toString(keysArr));
                for (int i = 0; i < keysArr.length; i++) {
                    JSONObject synonymAndNoun = (JSONObject) fortSillData.get(keysArr[i]);
                    // System.out.println("syn and noun: " + synonymAndNoun);
                    JSONArray synonym = (JSONArray)synonymAndNoun.get("synonym");
                    for (Object eachSynonymValue : synonym) {
                        if (String.valueOf(eachSynonymValue).equals(userActionVerb)) {
                            // System.out.println(eachSynonymValue);
                            possibleMatchedSynonym = String.valueOf(eachSynonymValue);
                            possibleMatchedKey = keysArr[i];
                            break;
                        }
                    }
                    if (possibleMatchedSynonym != null && possibleMatchedKey != null) {
                        break;
                    }
                }

                if (possibleMatchedSynonym != null) {
                    JSONObject jsonObject = (JSONObject) fortSillData.get(possibleMatchedKey);
                    String isUserActionNounExist = checkUserEnterNounValid(jsonObject, userActionNoun);

                    if (isUserActionNounExist != null) {
                        verbAndNoun.put("verb", possibleMatchedKey);
                        verbAndNoun.put("noun", isUserActionNounExist);
                        return true;
                    }
                    // if user noun is null then false
                    verbAndNoun.put("", "");
                    return false;
                } else {
                    // it means synonym is not found (verb error)
                    verbAndNoun.put("", "");
                    return false;
                }
            }
            verbAndNoun.put("", "");
            return false;
    }

    // method to get appropriate noun
    private String checkUserEnterNounValid(JSONObject _jsonObject, String userActionNoun) {
        JSONArray possibleNounArray = (JSONArray) _jsonObject.get("noun");
        // System.out.println(possibleNounArray);
        for (int i = 0; i < possibleNounArray.size(); i++) {
            if (String.valueOf(possibleNounArray.get(i)).equals(userActionNoun)) {
                return String.valueOf(possibleNounArray.get(i));
            }
        }
        return null;
    }
}
