package org.scorpion.calc.montecarlo.path;

import org.scorpion.calc.montecarlo.PathGenerator;
import org.scorpion.calc.montecarlo.RandomNumberGenerator;

import java.util.List;

public class RandomWalk implements PathGenerator {

    private RandomNumberGenerator rng;

    public RandomWalk(RandomNumberGenerator rng) {
        this.rng = rng;
        rng.skip(1L);
    }

    @Override
    public void generateOneBrownianPath(List<Double> path) {
        path.addAll(rng.getGaussianNumbers());
    }
}
