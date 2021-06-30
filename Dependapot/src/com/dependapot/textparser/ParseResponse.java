package com.dependapot.textparser;

public class ParseResponse {
    private String noun = null;
    private String verb = null;

    public ParseResponse(String _noun, String _verb) {
        this.noun = _noun;
        this.verb = _verb;
    }

    public String getNoun() {
        return noun;
    }

    public String getVerb() {
        return verb;
    }

    @Override
    public String toString() {
        return "ParseResponse{" +
                "noun='" + noun + '\'' +
                ", verb='" + verb + '\'' +
                '}';
    }
}
