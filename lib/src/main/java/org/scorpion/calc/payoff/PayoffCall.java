package org.scorpion.calc.payoff;

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
