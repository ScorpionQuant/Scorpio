package org.scorpion.calc.analytic.discount;

import org.junit.Test;
import org.scorpion.calc.analytic.DiscountFactor;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class FlatDiscountFactorTest {

    private DiscountFactor df = new FlatDiscountFactor(.05);

    @Test
    public void testDaysToYear() throws Exception {
        LocalDate start = LocalDate.of(2017, 10, 6);
        LocalDate end = LocalDate.of(2017, 10, 13);
        double days = 7d;
        assertEquals(df.discount(start, end), df.discount(days / 365d), 1e-4);

        start = LocalDate.of(2017, 8, 6);
        end = LocalDate.of(2017, 10, 13);
        days = 68d;
        assertEquals(df.discount(start, end), df.discount(days/365d), 1e-4);

        start = LocalDate.of(2015, 10, 6);
        end = LocalDate.of(2017, 10, 13);
        days = 7d;
        assertEquals(df.discount(start, end), df.discount(days/365d + 2), 1e-4);
    }

}