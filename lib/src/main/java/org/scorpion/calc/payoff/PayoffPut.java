package org.scorpion.calc.payoff;

public class PayoffPut implements Payoff {

    private double strike;

    public PayoffPut(double strike) {
        this.strike = strike;
    }

    @Override
    public double getPayoff(double spot) {
        return Math.max(strike - spot, 0);
    }
}
