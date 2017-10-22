package org.scorpion.calc.montecarlo;

public abstract class MCSinglePathResult {

    private double singlePathResult;

    public MCSinglePathResult(double singlePathResult) {
        this.singlePathResult = singlePathResult;
    }

    public double getSinglePathResult() {
        return singlePathResult;
    }
}
