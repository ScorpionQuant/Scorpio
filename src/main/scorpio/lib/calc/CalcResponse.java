package lib.calc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lib.message.Payload;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CalcResponse implements Payload {
    private static final long serialVersionUID = 42L;

    private final String symbol;
    private final Valuation valuation;
    private final long responseTime;

    @JsonCreator
    public CalcResponse(@JsonProperty("symbol") String symbol,
                        @JsonProperty("valuation") Valuation valuation) {
        this.symbol = symbol;
        this.valuation = valuation;
        this.responseTime = System.currentTimeMillis();
    }

    public String getSymbol() {
        return symbol;
    }

    public Valuation getValuation() {
        return valuation;
    }

    public long getResponseTime() {
        return responseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CalcResponse response = (CalcResponse) o;

        return new EqualsBuilder()
                .append(responseTime, response.responseTime)
                .append(symbol, response.symbol)
                .append(valuation, response.valuation)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(symbol)
                .append(valuation)
                .append(responseTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbol", symbol)
                .append("responseTime", responseTime)
                .append("valuation", valuation)
                .toString();
    }
}
