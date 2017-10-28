package org.scorpion.calc.montecarlo.engine;

import org.junit.Test;
import org.scorpion.calc.discount.DiscountFactor;
import org.scorpion.calc.discount.FlatDiscountFactor;
import org.scorpion.calc.montecarlo.*;
import org.scorpion.calc.montecarlo.path.RandomWalk;
import org.scorpion.calc.montecarlo.rng.ParkMillerRandomNumberGenerator;
import org.scorpion.calc.payoff.Payoff;
import org.scorpion.calc.payoff.PayoffCall;

import static org.junit.Assert.assertEquals;

public class VanillaMCEngineTest {

    private long dimension = 252L;
    private long numberOfPaths = 10000L;

    private Payoff payoff = new PayoffCall(110);
    private DiscountFactor df = new FlatDiscountFactor(.05);
    private RandomNumberGenerator rng = new ParkMillerRandomNumberGenerator(dimension, 1);
    private PathGenerator pathGen = new RandomWalk(rng);

    @Test
    public void testMCVanilla() throws Exception {

        MonteCarloEngine engine = new VanillaMCEngine(
                .03,
                100,
                1,
                .3,
                pathGen,
                payoff,
                df,
                new MonteCarloParam(numberOfPaths, dimension)
        );

        MCStatistics stat = engine.doSimulate();

        double res = stat.value();
        assertEquals(9.0571, res, 1e-1);
    }

}