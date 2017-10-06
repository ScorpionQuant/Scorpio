package org.scorpion.calc.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.scorpion.calc.OptionInput;
import org.scorpion.product.ExerciseType;
import org.scorpion.product.PutCall;

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
