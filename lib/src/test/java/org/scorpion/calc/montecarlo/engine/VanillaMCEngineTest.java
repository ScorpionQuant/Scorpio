package org.scorpion.calc.montecarlo.engine;

import org.junit.Test;
import org.scorpion.calc.discount.DiscountFactor;
import org.scorpion.calc.discount.FlatDiscountFactor;
import org.scorpion.calc.montecarlo.MCStatistics;
import org.scorpion.calc.montecarlo.MonteCarloEngine;
import org.scorpion.calc.montecarlo.MonteCarloParam;
import org.scorpion.calc.montecarlo.RandomNumberGenerator;
import org.scorpion.calc.montecarlo.rng.ParkMillerRandomNumberGenerator;
import org.scorpion.calc.payoff.Payoff;
import org.scorpion.calc.payoff.PayoffCall;

import static org.junit.Assert.assertEquals;

public class VanillaMCEngineTest {

    private long dimension = 252L;
    private long numberOfPaths = 10000L;

    private Payoff payoff = new PayoffCall(100);
    private DiscountFactor df = new FlatDiscountFactor(.05);
    private RandomNumberGenerator rng = new ParkMillerRandomNumberGenerator(dimension, 10000);

    @Test
    public void testMCVanilla() throws Exception {
        MonteCarloEngine engine = new VanillaMCEngine(
                .03,
                110,
                1,
                .3,
                new MonteCarloParam(numberOfPaths, dimension),
                payoff,
                df,
                rng);
        MCStatistics stat = engine.doSimulate();

        double res = stat.value();
        assertEquals(19.4795, res, 1e-1);
    }

}