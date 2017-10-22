package org.scorpion.calc.montecarlo.rng;

import org.scorpion.calc.montecarlo.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class ParkMillerRandomNumberGenerator implements RandomNumberGenerator {

    private long dimensionality;
    private long initialSeed;
    private ParkMiller innerGenerator;

    private double reciprocal;

    public ParkMillerRandomNumberGenerator(long dimensionality, long initialSeed) {
        this.dimensionality = dimensionality;
        this.initialSeed = initialSeed;
        this.innerGenerator = new ParkMiller(initialSeed);

        this.reciprocal = 1d / (1d + innerGenerator.max());
    }

    @Override
    public void resetDimensionality(long dimensionality) {
        this.dimensionality = dimensionality;
        innerGenerator.setSeed(initialSeed);
    }

    @Override
    public void resetInitialSeed(long initialSeed) {
        this.initialSeed = initialSeed;
        innerGenerator.setSeed(initialSeed);
    }

    @Override
    public void reset() {
        innerGenerator.setSeed(initialSeed);
    }

    @Override
    public List<Double> getUniforms() {
        List<Double> res = new ArrayList<>();
        for (int i = 0; i < dimensionality; ++i) res.add(innerGenerator.getOneRandomInteger() * reciprocal);
        return res;
    }

    @Override
    public void skip(long numberOfPaths) {
        for (int i = 0; i < numberOfPaths; ++i) {
            getUniforms();
        }
    }
}
