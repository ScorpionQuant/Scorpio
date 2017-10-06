package lib.calc;

import com.fasterxml.jackson.annotation.*;
import lib.calc.input.CommodityOptionInput;
import lib.calc.input.EquityOptionInput;
import lib.message.Payload;
import lib.product.ExerciseType;
import lib.product.PutCall;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EquityOptionInput.class, name = "EquityOptionInput"),
        @JsonSubTypes.Type(value = CommodityOptionInput.class, name = "CommodityOptionInput")
})
public abstract class OptionInput implements Payload {
    private static final long serialVersionUID = 42L;

    private final double underlyingPrice;

    private final double strikePrice;

    private final double riskFreeRate;

    private final double volatility;

    private final double timeToMaturity;

    private final PutCall putCall;

    private final ExerciseType exerciseType;

    @JsonCreator
    public OptionInput(@JsonProperty("underlyingPrice") double underlyingPrice,
                       @JsonProperty("strikePrice") double strikePrice,
                       @JsonProperty("riskFreeRate") double riskFreeRate,
                       @JsonProperty("volatility") double volatility,
                       @JsonProperty("timeToMaturity") double timeToMaturity,
                       @JsonProperty("putCall") PutCall putCall,
                       @JsonProperty("exerciseType") ExerciseType exerciseType) {
        this.underlyingPrice = underlyingPrice;
        this.strikePrice = strikePrice;
        this.riskFreeRate = riskFreeRate;
        this.volatility = volatility;
        this.timeToMaturity = timeToMaturity;
        this.putCall = putCall;
        this.exerciseType = exerciseType;
    }

    public double getUnderlyingPrice() {
        return underlyingPrice;
    }

    public double getStrikePrice() {
        return strikePrice;
    }

    public double getRiskFreeRate() {
        return riskFreeRate;
    }

    public double getVolatility() {
        return volatility;
    }

    public double getTimeToMaturity() {
        return timeToMaturity;
    }

    public PutCall getPutCall() {
        return putCall;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public abstract double getCostOfCarry();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OptionInput that = (OptionInput) o;

        return new EqualsBuilder()
                .append(underlyingPrice, that.underlyingPrice)
                .append(strikePrice, that.strikePrice)
                .append(riskFreeRate, that.riskFreeRate)
                .append(volatility, that.volatility)
                .append(timeToMaturity, that.timeToMaturity)
                .append(putCall, that.putCall)
                .append(exerciseType, that.exerciseType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(underlyingPrice)
                .append(strikePrice)
                .append(riskFreeRate)
                .append(volatility)
                .append(timeToMaturity)
                .append(putCall)
                .append(exerciseType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("underlyingPrice", underlyingPrice)
                .append("strikePrice", strikePrice)
                .append("riskFreeRate", riskFreeRate)
                .append("volatility", volatility)
                .append("timeToMaturity", timeToMaturity)
                .append("putCall", putCall)
                .append("exerciseType", exerciseType)
                .toString();
    }
}
