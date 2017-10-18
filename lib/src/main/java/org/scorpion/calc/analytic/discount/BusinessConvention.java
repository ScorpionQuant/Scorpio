package org.scorpion.calc.analytic.discount;

public enum BusinessConvention {

    // todo Basically this is for Fixed Income, do we really need this?
    ActualActual("Actual / Actual"),
    Actual360("Actual / 360"),
    ThreeSixty360("360 / 360");

    private String name;

    BusinessConvention(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
