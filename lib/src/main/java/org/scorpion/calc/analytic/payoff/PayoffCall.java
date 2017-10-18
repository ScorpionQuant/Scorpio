package org.scorpion.calc.analytic.payoff;

import org.scorpion.calc.analytic.Payoff;

public class PayoffCall implements Payoff {

    private double strike;

    public PayoffCall(double strike) {
        this.strike = strike;
    }

    @Override
    public double getPayoff(double spot) {
        return Math.max(spot - strike, 0);
    }
}
