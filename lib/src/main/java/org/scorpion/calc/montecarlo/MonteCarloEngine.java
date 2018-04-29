package org.scorpion.calc.montecarlo;

import org.scorpion.calc.discount.DiscountFactor;
import org.scorpion.calc.payoff.Payoff;

public abstract class MonteCarloEngine {

    /* ---- this is product specialized parameters ---- */
    protected double costOfCarry;
    protected double initialSpot;
    protected double timeToMaturity;
    protected double volatility;
    /* ---- this is product specialized parameters ---- */

    protected PathGenerator pathGen;
    protected Payoff payoff;
    protected DiscountFactor discountFactor;

    protected MonteCarloParam param;

    /* ---- this is the calculate specialized parameters ---- */
    protected long numberOfTimeSteps;
    protected double stepLength;
    protected double variance;
    protected double rootVariance;
    protected double itoCorrelation;
    /* ---- this is the calculate specialized parameters ---- */

    public MonteCarloEngine(double costOfCarry,
                            double initialSpot,
                            double timeToMaturity,
                            double volatility,
                            PathGenerator pathGen,
                            Payoff payoff,
                            DiscountFactor discountFactor,
                            MonteCarloParam param) {
        this.costOfCarry = costOfCarry;
        this.initialSpot = initialSpot;
        this.timeToMaturity = timeToMaturity;
        this.volatility = volatility;
        this.pathGen = pathGen;
        this.payoff = payoff;
        this.discountFactor = discountFactor;
        this.param = param;
        this.numberOfTimeSteps = param.getNumberOfTimeStep();
        this.stepLength = timeToMaturity / numberOfTimeSteps;
        this.variance = stepLength * volatility * volatility;
        this.rootVariance = Math.sqrt(variance);
        this.itoCorrelation = -.5 * variance;
    }

    public abstract MCSinglePathResult doOnePath () throws Exception;

    public abstract MCStatistics doSimulate() throws Exception;
    
}
