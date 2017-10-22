package org.scorpion.calc.montecarlo.rng;

import org.junit.Test;
import org.scorpion.calc.montecarlo.RandomNumberGenerator;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParkMillerRandomNumberGeneratorTest {

    private long dimension = 10000;
    private RandomNumberGenerator rng = new ParkMillerRandomNumberGenerator(dimension, 5);

    @Test
    public void test() {

        List<Double> normalRandoms = rng.getGaussianNumbers();

        double mean = normalRandoms.stream().reduce(0d, (x, y) -> x + y) / dimension;

        assertEquals(0d, mean, 1e-2);
    }

}