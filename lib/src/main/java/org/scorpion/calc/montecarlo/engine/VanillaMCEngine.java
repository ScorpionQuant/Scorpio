package org.scorpion.calc.montecarlo.engine;

import org.scorpion.calc.discount.DiscountFactor;
import org.scorpion.calc.montecarlo.*;
import org.scorpion.calc.montecarlo.singlepathresult.VanillaSinglePathResult;
import org.scorpion.calc.montecarlo.statistic.VanillaStatistics;
import org.scorpion.calc.payoff.Payoff;

import java.util.ArrayList;
import java.util.List;

public class VanillaMCEngine implements MonteCarloEngine {

    private double costOfCarry;
    private double initialSpot;
    private double timeToMaturity;
    private double volatility;

    private MonteCarloParam param;

    private RandomNumberGenerator rng;
    private Payoff payoff;
    private DiscountFactor discountFactor;

    /* ---- this is the calculate specialized parameters ---- */
    private long dimensionality;
    private double stepLength;
    private double variance;
    private double rootVariance;
    private double itoCorrelation;
    /* ---- this is the calculate specialized parameters ---- */

    public VanillaMCEngine(
            double costOfCarry,
            double initialSpot,
            double timeToMaturity,
            double volatility,
            MonteCarloParam param,
            Payoff payoff,
            DiscountFactor discountFactor,
            RandomNumberGenerator rng) {
        this.costOfCarry = costOfCarry;
        this.initialSpot = initialSpot;
        this.timeToMaturity = timeToMaturity;
        this.volatility = volatility;
        this.param = param;
        this.payoff = payoff;
        this.discountFactor = discountFactor;
        this.rng = rng;

        this.dimensionality = param.getDimensionality();

        this.stepLength = timeToMaturity / dimensionality;
        this.variance = stepLength * volatility * volatility;
        this.rootVariance = Math.sqrt(variance);
        this.itoCorrelation = -.5 * variance;
    }

    @Override
    public MCSinglePathResult doOnePath() throws Exception {
        double res = Math.log(initialSpot);

        List<Double> randomNumbers = rng.getGaussianNumbers();
        if (dimensionality != randomNumbers.size()) {
            throw new RuntimeException("The dimensionality should be match");
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
        rng.skip(1);
        List<MCSinglePathResult> res = new ArrayList<>();
        long numberOfPaths = param.getNumberOfPaths();
        for (int i = 0; i < numberOfPaths; ++i) {
            MCSinglePathResult singlePathResult = doOnePath();
            res.add(singlePathResult);
        }
        return new VanillaStatistics(res);
    }
}
