package lib.calc.input;

import lib.calc.OptionInput;
import lib.product.ExerciseType;
import lib.product.PutCall;

public class EquityOptionInput extends OptionInput {

    final private double dividendRate;

    public EquityOptionInput(double underlyingPrice,
                             double strikePrice,
                             double riskFreeRate,
                             double dividendRate,
                             double volatility,
                             double timeToMaturity,
                             PutCall putCall,
                             ExerciseType exerciseType) {
        super(underlyingPrice, strikePrice, riskFreeRate, volatility, timeToMaturity, putCall, exerciseType);
        this.dividendRate = dividendRate;
    }

    public double getDividendRate() {
        return dividendRate;
    }

    @Override
    public double getCostOfCarry() {
        return getRiskFreeRate() - dividendRate;
    }
}
