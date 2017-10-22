package org.scorpion.calc.discount;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FlatDiscountFactor implements DiscountFactor {

    // todo Let's first assume actual / actual

    private double interestRate;

    public FlatDiscountFactor(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double discount(LocalDate start, LocalDate end) {
        return discount(daysToYear(start, end));
    }

    @Override
    public double discount(LocalDate end) {
        return discount(LocalDate.now(), end);
    }

    @Override
    public double discount(double timePeriod) {
        return Math.exp(-interestRate * timePeriod);
    }

    private double daysToYear(LocalDate start, LocalDate end) {
        double res = 0d;
        double days = start.isLeapYear() ? 366 : 365;

        int startYear = start.getYear();
        int endYear = end.getYear();

        if (start.getYear() == end.getYear()) {
            return start.until(end, ChronoUnit.DAYS)/ days;
        }
        /* Here we need to count the last day, thus we plus 1 */
        res += (start.until(LocalDate.of(start.getYear(), 12, 31), ChronoUnit.DAYS) + 1) / days;

        days = end.isLeapYear() ? 366 : 365;
        res += LocalDate.of(end.getYear(), 1, 1).until(end, ChronoUnit.DAYS) / days;

        return endYear - startYear - 1 + res;
    }
}
