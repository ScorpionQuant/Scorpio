package lib.calc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lib.calc.calculator.CalculatorType;
import lib.message.Payload;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CalcRequest implements Payload {
    private static final long serialVersionUID = 42L;

    private String symbol;

    private CalculatorType calculatorType;

    private OptionInput input;

    private long reqTime;

    @JsonCreator
    public CalcRequest(@JsonProperty("symbol") String symbol,
                       @JsonProperty("calculatorType") CalculatorType calculatorType,
                       @JsonProperty("input") OptionInput input) {
        this.symbol = symbol;
        this.calculatorType = calculatorType;
        this.input = input;
        this.reqTime = System.currentTimeMillis();
    }

    public String getSymbol() {
        return symbol;
    }

    public CalculatorType getCalculatorType() {
        return calculatorType;
    }

    public OptionInput getInput() {
        return input;
    }

    public long getReqTime() {
        return reqTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CalcRequest that = (CalcRequest) o;

        return new EqualsBuilder()
                .append(symbol, that.symbol)
                .append(reqTime, that.reqTime)
                .append(calculatorType, that.calculatorType)
                .append(input, that.input)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(symbol)
                .append(reqTime)
                .append(calculatorType)
                .append(input)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbol", symbol)
                .append("reqTime", reqTime)
                .append("calculatorType", calculatorType)
                .append("input", input)
                .toString();
    }
}
