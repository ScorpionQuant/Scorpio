package lib.calc;

public class Valuation {

    final private double value;

    final private double referenceVol;

    final private double delta;
    final private double gamma;

    final private double theta;
    final private double thetaCalendarDay;
    final private double thetaTradingDay;

    final private double vega;

    final private double rho;

    public Valuation(double value,
                     double referenceVol,
                     double delta,
                     double gamma,
                     double theta,
                     double thetaCalendarDay,
                     double thetaTradingDay,
                     double vega,
                     double rho) {
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
}
