package lib.calc.calculator.impl;

import lib.calc.Calculator;
import lib.calc.OptionInput;
import lib.calc.calculator.GeneralizedBSM;
import lib.calc.input.*;
import lib.calc.Valuation;
import lib.product.ExerciseType;
import lib.product.PutCall;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneralizedBSMTest {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new GeneralizedBSM();
    }

    @Test
    public void testEquity() {
        OptionInput equityCall = new EquityOptionInput(
                110d,
                100d,
                .05,
                .3,
                1d,
                PutCall.CALL,
                ExerciseType.EUROPEAN,
                .02
        );

        OptionInput equityPut = new EquityOptionInput(
                110d,
                100d,
                .05,
                .3,
                1d,
                PutCall.PUT,
                ExerciseType.EUROPEAN,
                .02
        );

        Valuation equityCallOutput = calculator.calculate(equityCall);

        assertEquals(19.4795, equityCallOutput.getValue(), 1e-4);
        assertEquals(0.700725, equityCallOutput.getDelta(), 1e-6);
        assertEquals(0.0100862, equityCallOutput.getGamma(), 1e-7);
        assertEquals(-0.0187133, equityCallOutput.getThetaCalendarDay(), 1e-7);
        assertEquals(36.6129, equityCallOutput.getVega(), 1e-4);
        assertEquals(57.6003, equityCallOutput.getRho(), 1e-4);

        Valuation equityPutOutput = calculator.calculate(equityPut);
        assertEquals(6.7806, equityPutOutput.getValue(), 1e-4);
        assertEquals(-0.279473, equityPutOutput.getDelta(), 1e-6);
        assertEquals(0.0100862, equityPutOutput.getGamma(), 1e-7);
        assertEquals(-0.0115908, equityPutOutput.getThetaCalendarDay(), 1e-7);
        assertEquals(36.6129, equityPutOutput.getVega(), 1e-4);
        assertEquals(-37.5227, equityPutOutput.getRho(), 1e-4);
    }

    @Test
    public void testCommodity() {
        OptionInput commodityCall = new CommodityOptionInput(
                110d,
                100d,
                .05,
                .3,
                1d,
                PutCall.CALL,
                ExerciseType.EUROPEAN,
                .015,
                .025
        );

        OptionInput commodityPut = new CommodityOptionInput(
                110d,
                100d,
                .05,
                .3,
                1d,
                PutCall.PUT,
                ExerciseType.EUROPEAN,
                .015,
                .025
        );

        Valuation commodityCallOutput = calculator.calculate(commodityCall);

        assertEquals(20.2603, commodityCallOutput.getValue(), 1e-4);
        assertEquals(0.718867, commodityCallOutput.getDelta(), 1e-6);
        assertEquals(0.00999104, commodityCallOutput.getGamma(), 1e-8);
        assertEquals(-0.0207948, commodityCallOutput.getThetaCalendarDay(), 1e-7);
        assertEquals(36.2675, commodityCallOutput.getVega(), 1e-4);
        assertEquals(58.8151, commodityCallOutput.getRho(), 1e-4);

        Valuation commodityPutOutput = calculator.calculate(commodityPut);
        assertEquals(6.47774, commodityPutOutput.getValue(), 1e-5);
        assertEquals(-0.271183, commodityPutOutput.getDelta(), 1e-6);
        assertEquals(0.00999104, commodityPutOutput.getGamma(), 1e-8);
        assertEquals(-0.010748, commodityPutOutput.getThetaCalendarDay(), 1e-7);
        assertEquals(36.2675, commodityPutOutput.getVega(), 1e-4);
        assertEquals(-36.3079, commodityPutOutput.getRho(), 1e-4);
    }
}