package org.scorpion.calc.montecarlo.engine;

import com.google.common.base.Preconditions;
import org.scorpion.calc.discount.DiscountFactor;
import org.scorpion.calc.montecarlo.*;
import org.scorpion.calc.montecarlo.singlepathresult.VanillaSinglePathResult;
import org.scorpion.calc.montecarlo.statistic.VanillaStatistics;
import org.scorpion.calc.montecarlo.variancereduction.StratifiedSampling;
import org.scorpion.calc.montecarlo.variancereduction.StratifiedSamplingUniforms;
import org.scorpion.calc.payoff.Payoff;

import java.util.ArrayList;
import java.util.List;

public class VanillaStratifiedSamplingEngine extends MonteCarloEngine {

    private long numberOfPaths;
    private long numberOfStrata;
    private Double currentTerminal;
    private StratifiedSampling strata;

    public VanillaStratifiedSamplingEngine(double costOfCarry,
                                           double initialSpot,
                                           double timeToMaturity,
                                           double volatility,
                                           PathGenerator pathGen,
                                           Payoff payoff,
                                           DiscountFactor discountFactor,
                                           MonteCarloParam param,
                                           long numberOfStrata) {
        super(costOfCarry, initialSpot, timeToMaturity, volatility, pathGen, payoff, discountFactor, param);
        // todo we can config the seed there in the configuration, now we use the hard code.
        this.numberOfPaths = param.getNumberOfPaths();
        this.numberOfStrata = numberOfStrata;
        strata = new StratifiedSamplingUniforms(numberOfPaths,100L);
    }

    @Override
    public MCSinglePathResult doOnePath() throws Exception {
        double res = Math.log(initialSpot);

        List<Double> path = new ArrayList<>((int) numberOfTimeSteps);
        for (int i = 0; i < numberOfTimeSteps; ++i) {
            path.add(0d);
        }

        path.set((int) numberOfTimeSteps - 1, currentTerminal);
        pathGen.generateOneBrownianPath(path);

        if (numberOfTimeSteps != path.size()) {
            throw new RuntimeException("The number of time steps should be match");
        }

        for (Double entry : path) {
            res += costOfCarry * stepLength + itoCorrelation + rootVariance * entry;
        }
        res = Math.exp(res);

        return new VanillaSinglePathResult(discountFactor.discount(timeToMaturity) * payoff.getPayoff(res));
    }

    @Override
    public MCStatistics doSimulate() throws Exception {
        List<MCSinglePathResult> res = new ArrayList<>();
        List<Double> terminals = strata.getTerminalValue(numberOfStrata);

        Preconditions.checkArgument(terminals.size() == numberOfPaths);

        for (Double entry : terminals) {
            currentTerminal = entry;
            MCSinglePathResult singlePathResult = doOnePath();
            res.add(singlePathResult);
        }

        return new VanillaStatistics(res);
    }
}
