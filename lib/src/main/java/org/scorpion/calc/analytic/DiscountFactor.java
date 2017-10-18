package org.scorpion.calc.analytic;

import java.time.LocalDate;

public interface DiscountFactor {

    double discount(LocalDate start, LocalDate end);

    double discount(LocalDate end);

    double discount(double timePeriod);
}
