package lib.calc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lib.message.Payload;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Valuation implements Payload {
    private static final long serialVersionUID = 42L;

    final private double value;

    final private double referenceVol;

    final private double delta;
    final private double gamma;

    final private double theta;
    final private double thetaCalendarDay;
    final private double thetaTradingDay;

    final private double vega;

    final private double rho;

    @JsonCreator
    public Valuation(@JsonProperty("value") double value,
                     @JsonProperty("referenceVol") double referenceVol,
                     @JsonProperty("delta") double delta,
                     @JsonProperty("gamma") double gamma,
                     @JsonProperty("theta") double theta,
                     @JsonProperty("thetaCalendarDay") double thetaCalendarDay,
                     @JsonProperty("thetaTradingDay") double thetaTradingDay,
                     @JsonProperty("vega") double vega,
                     @JsonProperty("rho") double rho) {
        this.value = value;
        this.referenceVol = referenceVol;
        this.delta = delta;
        this.gamma = gamma;
        this.theta = theta;
        this.thetaCalendarDay = thetaCalendarDay;
        this.thetaTradingDay = thetaTradingDay;
        this.vega = vega;
        this.rho = rho;
    }

    public double getValue() {
        return value;
    }

    public double getReferenceVol() {
        return referenceVol;
    }

    public double getDelta() {
        return delta;
    }

    public double getGamma() {
        return gamma;
    }

    public double getTheta() {
        return theta;
    }

    public double getThetaCalendarDay() {
        return thetaCalendarDay;
    }

    public double getThetaTradingDay() {
        return thetaTradingDay;
    }

    public double getVega() {
        return vega;
    }

    public double getRho() {
        return rho;
    }

    public static Valuation mock() {
        double tradingDay = Math.random()*300;
        double calendarDay = tradingDay + tradingDay/7 * 2;
        return new Valuation(Math.random()*1000, Math.random()*100, Math.random(), Math.random(), Math.random(),
                calendarDay, tradingDay, Math.random(), Math.random());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Valuation valuation = (Valuation) o;

        return new EqualsBuilder()
                .append(value, valuation.value)
                .append(referenceVol, valuation.referenceVol)
                .append(delta, valuation.delta)
                .append(gamma, valuation.gamma)
                .append(theta, valuation.theta)
                .append(thetaCalendarDay, valuation.thetaCalendarDay)
                .append(thetaTradingDay, valuation.thetaTradingDay)
                .append(vega, valuation.vega)
                .append(rho, valuation.rho)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .append(referenceVol)
                .append(delta)
                .append(gamma)
                .append(theta)
                .append(thetaCalendarDay)
                .append(thetaTradingDay)
                .append(vega)
                .append(rho)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("referenceVol", referenceVol)
                .append("delta", delta)
                .append("gamma", gamma)
                .append("theta", theta)
                .append("thetaCalendarDay", thetaCalendarDay)
                .append("thetaTradingDay", thetaTradingDay)
                .append("vega", vega)
                .append("rho", rho)
                .toString();
    }
}
