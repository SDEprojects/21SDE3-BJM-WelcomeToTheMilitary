package com.welcomeToTheMilitary.character;

public enum Rank {
    E1("PV1",1),
    E2("PV2", 2),
    E3("PFC", 3),
    E4("SPC", 4),
    E5("SGT", 5),
    E6("SSG", 6),
    E7("SFC", 7),
    E8("1SG", 8),
    E9("CSM", 9);

    private String abbreviation;
    private int value;

    Rank(String Abbreviation, int value) {
        this.abbreviation = Abbreviation;
        this.value = value;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

