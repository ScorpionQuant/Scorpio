package lib.calc.calculator;

import lib.calc.Calculator;
import lib.calc.OptionInput;
import lib.calc.Valuation;
import lib.calc.math.NormalDistribution;
import lib.product.PutCall;

public class GeneralizedBSM implements Calculator {

    /**
     * This is Generalized Black-Scholes-Merton
     */
    @Override
    public Valuation calculate(OptionInput input) {
        if (input.getPutCall().equals(PutCall.CALL)) {
            return calculateCall(
                    input.getRiskFreeRate(),
                    input.getUnderlyingPrice(),
                    input.getStrikePrice(),
                    input.getVolatility(),
                    input.getTimeToMaturity(),
                    input.getCostOfCarry()
            );
        } else {
            return calculatePut(
                    input.getRiskFreeRate(),
                    input.getUnderlyingPrice(),
                    input.getStrikePrice(),
                    input.getVolatility(),
                    input.getTimeToMaturity(),
                    input.getCostOfCarry()
            );
        }
    }

    private Valuation calculateCall(
            double riskFreeRate,
            double underlyingPrice,
            double strikePrice,
            double volatility,
            double timeToMaturity,
            double costOfCarry) {
        double variance = volatility * volatility * timeToMaturity;
        double sqrtVariance = Math.sqrt(variance);
        double forward = underlyingPrice * Math.exp(costOfCarry * timeToMaturity);

        double d1 = 1 / sqrtVariance * (Math.log(forward / strikePrice) + .5 * variance);
        double d2 = d1 - sqrtVariance;

        double discount = Math.exp(-riskFreeRate * timeToMaturity);
        double pureDelta = NormalDistribution.cumulativeNormal(d1);
        double dividend = riskFreeRate - costOfCarry;
        double costOfCarryDiscount = Math.exp(-dividend * timeToMaturity);

        double sqrtTimeToMaturity = Math.sqrt(timeToMaturity);

        double normD1 = NormalDistribution.normalDensity(d1);
        double deltaD2 = NormalDistribution.cumulativeNormal(d2);

        double delta = costOfCarryDiscount * pureDelta;
        double gamma = costOfCarryDiscount * normD1 / underlyingPrice / sqrtVariance;
        double theta = underlyingPrice * costOfCarryDiscount * (pureDelta * dividend - normD1 * volatility * .5 / sqrtTimeToMaturity)
                - riskFreeRate * strikePrice * discount * deltaD2;
        double thetaCalendarDay = theta / 365.0;
        double thetaTradingDay = theta / 242.0;
        double vega = underlyingPrice * costOfCarryDiscount * normD1 * sqrtTimeToMaturity;
        double rho = strikePrice * timeToMaturity * discount * deltaD2;

        double price = discount * (forward * pureDelta - strikePrice * deltaD2);

        return new Valuation(price, volatility, delta, gamma, theta, thetaCalendarDay, thetaTradingDay, vega, rho);
    }

    private Valuation calculatePut(double riskFreeRate,
                                   double underlyingPrice,
                                   double strikePrice,
                                   double volatility,
                                   double timeToMaturity,
                                   double costOfCarry) {
        double variance = volatility * volatility * timeToMaturity;
        double sqrtVariance = Math.sqrt(variance);
        double forward = underlyingPrice * Math.exp(costOfCarry * timeToMaturity);

        double d1 = 1 / sqrtVariance * (Math.log(forward / strikePrice) + .5 * variance);
        double d2 = d1 - sqrtVariance;

        double discount = Math.exp(-riskFreeRate * timeToMaturity);
        double pureDelta = NormalDistribution.cumulativeNormal(-d1);
        double dividend = riskFreeRate - costOfCarry;
        double costOfCarryDiscount = Math.exp(-dividend * timeToMaturity);

        double sqrtTimeToMaturity = Math.sqrt(timeToMaturity);

        double normD1 = NormalDistribution.normalDensity(d1);
        double deltaD2 = NormalDistribution.cumulativeNormal(-d2);

        double delta = -costOfCarryDiscount * pureDelta;
        double gamma = costOfCarryDiscount * normD1 / underlyingPrice / sqrtVariance;
        double theta = -underlyingPrice * costOfCarryDiscount * (pureDelta * dividend + normD1 * volatility * .5 / sqrtTimeToMaturity)
                + riskFreeRate * strikePrice * discount * deltaD2;
        double thetaCalendarDay = theta / 365.0;
        double thetaTradingDay = theta / 242.0;
        double vega = underlyingPrice * costOfCarryDiscount * normD1 * sqrtTimeToMaturity;
        double rho = -strikePrice * timeToMaturity * discount * deltaD2;

        double price = discount * (strikePrice * deltaD2 - forward * pureDelta);

        return new Valuation(price, volatility, delta, gamma, theta, thetaCalendarDay, thetaTradingDay, vega, rho);
    }
}
