package com.dependapot.textparser;



public class TextParser {
    private String noun = null;
    private String verb = null;

    public TextParser() {
        // empty constructor
    }

    // private getter
    private String getVerb() {
        return this.verb;
    }

    private String getNoun() {
        return this.noun;
    }

    // this method will return an arraylist that contains verb and noun order
    // @return ParseResponse obj
    public ParseResponse receiveAction(String userActionInput, String post) {
        // trim the user action input
        boolean isValidActionInput = trimUserInput(userActionInput);
        boolean isOperableCommend = operateUserAction(isValidActionInput, post);
        if (isOperableCommend) {
            // return verbAndNoun;
            return new ParseResponse(getNoun(), getVerb());
        }

        // placeholder
        return new ParseResponse(getNoun(), getVerb());
    }

    // helper method to validate user input
    // delete word "The" or "the"
    // set up the verb and noun
    private boolean trimUserInput(String userInput) {
        if (userInput == null || userInput.length() == 0) {
            this.noun = "";
            this.verb = "";
            return false;
        }
        String trimmedInput = userInput.trim().toLowerCase();
        // split out to check if the sentence contains more then 2 words
        String splitTrimmedInput[] = trimmedInput.split(" ");
        if (splitTrimmedInput.length == 4) {
            this.verb = splitTrimmedInput[0];
            this.noun = splitTrimmedInput[3];
        } else if (splitTrimmedInput.length == 3) {
            this.verb = splitTrimmedInput[0];
            this.noun = splitTrimmedInput[2];
        } else if (splitTrimmedInput.length == 2) {
            this.verb = splitTrimmedInput[0];
            this.noun = splitTrimmedInput[1];
        } else {
            // some message
            this.verb = "";
            this.noun = "";
            System.out.println("Invalid input:\nDesired input format: verb + noun");
            return false;
        }
        return true;
    }

    // operate action
    private boolean operateUserAction(boolean isValidActionInput, String postType) {
        if (!isValidActionInput) {
            System.out.println("Invalid action");
            return false;
        }
        boolean isOperable = false;
        String verbInput = this.verb;
        String nounInput = this.noun;
        switch (verbInput) {
            // instruction for movement
            case "go":
            case "move":
            case "drive":
            case "walk":
            case "run":
                isOperable = moveAction(nounInput, postType);
                return isOperable;
            case "talk":
            case "approach":
            case "interact":
                isOperable = interactNPCAction(nounInput, postType);
                return isOperable;
            case "show":
            case "display":
                isOperable = displayMapAction(nounInput, postType);
                return isOperable;
            default:
                this.verb = "";
                this.noun = "";
                System.out.println("Sorry, the verb: " + verbInput + " is not supported");
                return isOperable;
        }
    }

    // method for "go || move || drive || walk || run"
    private boolean moveAction(String nounInput, String postType) {
        System.out.println("Move action");
        if (postType.equals("Fort Sill")) {
            // switch case for fort sill
            switch (nounInput) {
                case "dfac":
                case "barracks":
                case "church":
                case "px":
                case "commissary":
                case "gym":
                    return true;
                default:
                    this.noun = "";
                    return false;
            }
        } else if (postType.equals("Fort Bliss")) {
            // switch case for fort bliss
            switch (nounInput) {
                case "starbucks":
                case "freedom crossing":
                case "house":
                case "restaurant":
                case "theater":
                    return true;
                default:
                    this.noun = "";
                    return false;
            }
        } else {
            // place holder for now
            return false;
        }
    }

    // method for talking or interact with the NPC
    // method for "go || move || drive || walk || run"
    private boolean interactNPCAction(String nounInput, String postType) {
        System.out.println("interact action");
        if (postType.equals("Fort Sill")) {
            // switch case for fort sill
            switch (nounInput) {
                case "Brad":
                case "Jeremy":
                case "Rogers":
                case "Shad":
                case "Arturo":
                case "Mason":
                case "John":
                case "Brandon":
                case "Laginus":
                case "Soko":
                case "David":
                case "Stephen":
                    return true;
                default:
                    this.noun = "";
                    return false;
            }
        } else if (postType.equals("Fort Bliss")) {
            // switch case for fort bliss
            switch (nounInput) {
                case "e-5":
                case "e-6":
                case "e-7":
                case "e-8":
                case "e-9":
                    return true;
                default:
                    this.noun = "";
                    return false;
            }
        } else {
            // place holder for now
            return false;
        }
    }

    private boolean displayMapAction(String nounInput, String postType) {
        if (postType.equals("Fort Sill")) {
            switch (nounInput) {
                case "map":
                case "location":
                case "buildings":
                case "building":
                    return true;
                default:
                    this.noun = "";
                    System.out.println("possible noun=>\nmap | location | buildings| buildings");
                    return false;
            }
        }
        return false;
    }

}
