package com.welcomeToTheMilitary.textparser;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TextParserTest {
    private static TextParser textParser = new TextParser();
    private static ParseResponse response = null;
    private final String FORTSILL = "Fort Sill";
    private final String FORTBLISS = "Fort Bliss";


    @Test
    public void receiveActionSuccessForInteractNPC() throws IOException, ParseException {
        String successResponse = "talk to brad";
        response = textParser.receiveAction(successResponse, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("talk", actualVerb);
        assertEquals("brad", actualNoun);
    }

    @Test
    public void receiveActionSuccess() throws IOException, ParseException {
        String successResponse = "move to Starbucks";
        response = textParser.receiveAction(successResponse, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        String expectedVerb = "go";
        String expectedNoun = "starbucks";
        // System.out.println(actualVerb);
        assertEquals(expectedVerb, actualVerb);
        assertEquals(expectedNoun, actualNoun);
        System.out.println("receiveActionSuccessForBliss Pass");
    }


    @Test
    public void receiveActionEmptyInputFortSill() throws IOException, ParseException {
        // it returns an empty string when we get empty string from user
        String emptyResponse = "";
        response = textParser.receiveAction(emptyResponse, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
        System.out.println("receiveActionEmptyInput Pass");
    }

    @Test
    public void receiveActionEmptyInputFortBliss() throws IOException, ParseException {
        String emptyResponse = "";
        response = textParser.receiveAction(emptyResponse, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionNullInput() throws IOException, ParseException {
        // if the input is null somehow, it should return empty string
        String nullResponse = null;
        response = textParser.receiveAction(nullResponse, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }


    @Test
    public void receiveActionInvalidAction() throws IOException, ParseException  {
        // invalid verb
        // invalid verb should return empty string
        String invalidActionVerb = " ge eez";
        response = textParser.receiveAction(invalidActionVerb, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }


    @Test
    public void receiveActionInvalidNoun() throws IOException, ParseException {
        // invalid noun
        // should return both verb and noun with empty string
        String invalidActionNoun = "move asdf"; 
        response = textParser.receiveAction(invalidActionNoun, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();

        String expectedVerb = "";
        String expectedNoun = "";
        // System.out.println(actualVerb);
        assertEquals(expectedVerb, actualVerb);
        assertEquals(expectedNoun, actualNoun);
    }

    @Test
    public void receiveActionUse() throws IOException, ParseException {
        // invalid nouns now don't exist with 'use' command.
        String invalidActionNoun = "use milk";
        response = textParser.receiveAction(invalidActionNoun, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();

        String expectedVerb = "use";
        String expectedNoun = "milk";
        // System.out.println(actualVerb);
        assertEquals(expectedVerb, actualVerb);
        assertEquals(expectedNoun, actualNoun);
    }

    @Test
    public void receiveActionUseEmptyFail() throws IOException, ParseException {
        // invalid noun
        // should return both verb and noun with empty string
        String invalidActionNoun = "use";
        response = textParser.receiveAction(invalidActionNoun, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();

        String expectedVerb = "";
        String expectedNoun = "";
        // System.out.println(actualVerb);
        assertEquals(expectedVerb, actualVerb);
        assertEquals(expectedNoun, actualNoun);
    }


}