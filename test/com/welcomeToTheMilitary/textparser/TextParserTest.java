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
    public void receiveActionSuccessFortSill() throws IOException, ParseException {
        // test for success path for fort sill
        String successResponse = "go dfac";
        response = textParser.receiveAction(successResponse,FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("go", actualVerb);
        assertEquals("dfac", actualNoun);
        System.out.println("receiveActionSuccessForSill Pass");
    }

    @Test
    public void receiveActionSuccessForInteractNPCFortSill() throws IOException, ParseException {
        String successResponse = "talk to e-1";
        response = textParser.receiveAction(successResponse, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("talk", actualVerb);
        assertEquals("e-1", actualNoun);
    }

    @Test
    public void receiveActionSuccessForBliss() throws IOException, ParseException {
        String successResponse = "move starbucks";
        response = textParser.receiveAction(successResponse, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        // System.out.println(actualVerb);
        assertEquals("move", actualVerb);
        assertEquals("starbucks", actualNoun);
        System.out.println("receiveActionSuccessForBliss Pass");
    }

    @Test
    public void receiveActionSuccessFortBliss() throws IOException, ParseException {
        String successResponse = "talk e-7";
        response = textParser.receiveAction(successResponse, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("talk", actualVerb);
        assertEquals("e-7", actualNoun);
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
    public void receiveActionNullInputFortSill() throws IOException, ParseException {
        // if the input is null somehow, it should return empty string
        String nullResponse = null;
        response = textParser.receiveAction(nullResponse, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionNullInputFortBliss() throws IOException, ParseException {
        // testcase for null
        // it should return empty string for both verb and noun
        String nullResponse = null;
        response = textParser.receiveAction(nullResponse, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionInvalidActionFortSill() throws IOException, ParseException  {
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
    public void receiveActionInvalidActionFortBliss() throws IOException, ParseException {
        // invalid verb
        // should return both verb and noun with empty string
        String invalidActionVerb = "asdfasdf asdf";
        response = textParser.receiveAction(invalidActionVerb, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionInvalidNounFortSill() throws IOException, ParseException {
        // invalid noun
        // should return both verb and noun with empty string
        String invalidActionNoun = "move asdf";
        response = textParser.receiveAction(invalidActionNoun, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("move", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionInvalidNounFortBliss() throws IOException, ParseException {
        // invalid noun
        // should return both verb and noun with empty string
        String invalidActionNoun = "drive zxbzx";
        response = textParser.receiveAction(invalidActionNoun, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("drive", actualVerb);
        assertEquals("", actualNoun);
    }

}