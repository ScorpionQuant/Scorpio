package org.scorpion.calc.montecarlo;

public class MonteCarloParam {

    private long numberOfPaths;
    private long numberOfTimeStep;

    public MonteCarloParam(long numberOfPaths, long numberOfTimeStep) {
        this.numberOfPaths = numberOfPaths;
        this.numberOfTimeStep = numberOfTimeStep;
    }

    public long getNumberOfPaths() {
        return numberOfPaths;
    }

    public long getNumberOfTimeStep() {
        return numberOfTimeStep;
    }
}
