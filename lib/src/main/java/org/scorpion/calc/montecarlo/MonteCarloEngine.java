package org.scorpion.calc.montecarlo;

import org.scorpion.calc.discount.DiscountFactor;
import org.scorpion.calc.payoff.Payoff;

public abstract class MonteCarloEngine {

    protected PathGenerator pathGen;
    protected Payoff payoff;
    protected DiscountFactor discountFactor;

    public MonteCarloEngine(PathGenerator pathGen,
                            Payoff payoff,
                            DiscountFactor discountFactor) {
        this.pathGen = pathGen;
        this.payoff = payoff;
        this.discountFactor = discountFactor;
    }

    public abstract MCSinglePathResult doOnePath () throws Exception;

    public abstract MCStatistics doSimulate() throws Exception;
    
}
