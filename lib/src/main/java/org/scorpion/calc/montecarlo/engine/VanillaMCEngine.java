package org.scorpion.calc.montecarlo.engine;

import org.scorpion.calc.discount.DiscountFactor;
import org.scorpion.calc.montecarlo.*;
import org.scorpion.calc.montecarlo.singlepathresult.VanillaSinglePathResult;
import org.scorpion.calc.montecarlo.statistic.VanillaStatistics;
import org.scorpion.calc.payoff.Payoff;

import java.util.ArrayList;
import java.util.List;

public class VanillaMCEngine extends MonteCarloEngine {

    public VanillaMCEngine(double costOfCarry,
                           double initialSpot,
                           double timeToMaturity,
                           double volatility,
                           PathGenerator pathGen,
                           Payoff payoff,
                           DiscountFactor discountFactor,
                           MonteCarloParam param) {
        super(costOfCarry, initialSpot, timeToMaturity, volatility, pathGen, payoff, discountFactor, param);
    }

    @Override
    public MCSinglePathResult doOnePath() throws Exception {
        double res = Math.log(initialSpot);

        List<Double> randomNumbers = new ArrayList<>();
        pathGen.generateOneBrownianPath(randomNumbers);
        if (numberOfTimeSteps != randomNumbers.size()) {
            throw new RuntimeException("The number of time steps should be match");
        }
        /*
         * Here I use the log Euler rather than directly euler. Which gives more accurate outcomes
         *
         * Log Euler can be expressed as:
         * log S = log (initial underlying)
         * log S += costOfCarry * dt - .5* vol * vol * dt + vol * sqrt(dt)*random
         * S = exp (logS)
         */
        for (Double entry : randomNumbers) {
            res += costOfCarry * stepLength + itoCorrelation + rootVariance * entry;
        }
        res = Math.exp(res);

        return new VanillaSinglePathResult(discountFactor.discount(timeToMaturity) * payoff.getPayoff(res));
    }

    @Override
    public MCStatistics doSimulate() throws Exception {
        List<MCSinglePathResult> res = new ArrayList<>();
        long numberOfPaths = param.getNumberOfPaths();
        for (int i = 0; i < numberOfPaths; ++i) {
            MCSinglePathResult singlePathResult = doOnePath();
            res.add(singlePathResult);
        }
        return new VanillaStatistics(res);
    }
}
