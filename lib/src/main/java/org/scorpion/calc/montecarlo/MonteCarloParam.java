package org.scorpion.calc.montecarlo;

public class MonteCarloParam {

    private long numberOfPaths;
    private long dimensionality;

    public MonteCarloParam(long numberOfPaths, long dimensionality) {
        this.numberOfPaths = numberOfPaths;
        this.dimensionality = dimensionality;
    }

    public long getNumberOfPaths() {
        return numberOfPaths;
    }

    public long getDimensionality() {
        return dimensionality;
    }

}
