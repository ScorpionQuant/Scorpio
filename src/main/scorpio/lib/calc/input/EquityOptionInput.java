package lib.calc.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lib.calc.OptionInput;
import lib.product.ExerciseType;
import lib.product.PutCall;

public class EquityOptionInput extends OptionInput {

    private final double dividendRate;

    @JsonCreator
    public EquityOptionInput(@JsonProperty("underlyingPrice") double underlyingPrice,
                             @JsonProperty("strikePrice") double strikePrice,
                             @JsonProperty("riskFreeRate") double riskFreeRate,
                             @JsonProperty("volatility") double volatility,
                             @JsonProperty("timeToMaturity") double timeToMaturity,
                             @JsonProperty("putCall") PutCall putCall,
                             @JsonProperty("exerciseType") ExerciseType exerciseType,
                             @JsonProperty("dividendRate") double dividendRate) {
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
