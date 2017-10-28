package org.scorpion.calc.montecarlo.engine;

import org.junit.Test;
import org.scorpion.calc.discount.DiscountFactor;
import org.scorpion.calc.discount.FlatDiscountFactor;
import org.scorpion.calc.montecarlo.MCStatistics;
import org.scorpion.calc.montecarlo.MonteCarloEngine;
import org.scorpion.calc.montecarlo.MonteCarloParam;
import org.scorpion.calc.montecarlo.PathGenerator;
import org.scorpion.calc.montecarlo.path.BrownianBridge;
import org.scorpion.calc.payoff.Payoff;
import org.scorpion.calc.payoff.PayoffCall;

import static org.junit.Assert.assertEquals;

public class VanillaStratifiedSamplingEngineTest {

    private long numberOfTimeSteps = 1L;
    private long numberOfPaths = 10000L;

    private long numberOfStrata = 1000L;

    private Payoff payoff = new PayoffCall(110);
    private DiscountFactor df = new FlatDiscountFactor(.05);
    private PathGenerator pathGen = new BrownianBridge(numberOfTimeSteps, 1d);

    @Test
    public void testStratifiedMCVanilla() throws Exception {

        MonteCarloEngine engine = new VanillaStratifiedSamplingEngine(
                .03,
                100d,
                1d,
                .3,
                pathGen,
                payoff,
                df,
                new MonteCarloParam(numberOfPaths, numberOfTimeSteps),
                numberOfStrata
        );

        MCStatistics stat = engine.doSimulate();

        double res = stat.value();
        assertEquals(9.0571, res, 1e-2);
    }

}