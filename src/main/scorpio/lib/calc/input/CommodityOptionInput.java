package lib.calc.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lib.calc.OptionInput;
import lib.product.ExerciseType;
import lib.product.PutCall;

public class CommodityOptionInput extends OptionInput {

    private final double storageCost;
    private final double convenienceYield;

    @JsonCreator
    public CommodityOptionInput(@JsonProperty("underlyingPrice") double underlyingPrice,
                                @JsonProperty("strikePrice") double strikePrice,
                                @JsonProperty("riskFreeRate") double riskFreeRate,
                                @JsonProperty("volatility") double volatility,
                                @JsonProperty("timeToMaturity") double timeToMaturity,
                                @JsonProperty("putCall") PutCall putCall,
                                @JsonProperty("exerciseType") ExerciseType exerciseType,
                                @JsonProperty("storageCost") double convenienceYield,
                                @JsonProperty("convenienceYield")double storageCost) {
        super(underlyingPrice, strikePrice, riskFreeRate, volatility, timeToMaturity, putCall, exerciseType);
        this.storageCost = storageCost;
        this.convenienceYield = convenienceYield;
    }

    public double getStorageCost() {
        return storageCost;
    }

    public double getConvenienceYield() {
        return convenienceYield;
    }

    @Override
    public double getCostOfCarry() {
        return getRiskFreeRate() + storageCost - convenienceYield;
    }
}
