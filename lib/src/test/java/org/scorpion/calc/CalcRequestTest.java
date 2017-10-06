package org.scorpion.calc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.scorpion.calc.calculator.CalculatorType;
import org.scorpion.calc.input.EquityOptionInput;
import org.scorpion.product.ExerciseType;
import org.scorpion.product.PutCall;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalcRequestTest {
    private final static double delta = 1e-8;

    @Test
    public void testSerialization() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CalcRequest originRequest = new CalcRequest("0005.hk", CalculatorType.GBSM,
                new EquityOptionInput(100, 90, 0.03, 0.3, 100, PutCall.CALL, ExerciseType.EUROPEAN, 0));
        String requestStr = mapper.writeValueAsString(originRequest);
        assertNotNull(requestStr);
        CalcRequest convertedRequest = mapper.readValue(requestStr, CalcRequest.class);
        assertEquals(originRequest, convertedRequest);
    }

    @Test
    public void testDeserializeFromEquityOption() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        InputStream testStream1 = getClass().getClassLoader().getResourceAsStream("EquityOptionInput.json");
        CalcRequest request = mapper.readValue(testStream1, CalcRequest.class);
        assertEquals("0005.hk", request.getSymbol());
        assertEquals(CalculatorType.GBSM, request.getCalculatorType());
        assertEquals(EquityOptionInput.class, request.getInput().getClass());
        EquityOptionInput input = (EquityOptionInput) request.getInput();
        assertEquals(100d, input.getStrikePrice(), delta);
        assertEquals(90d, input.getUnderlyingPrice(), delta);
        assertEquals(0d, input.getDividendRate(), delta);
        assertEquals(0.03d, input.getRiskFreeRate(), delta);
        assertEquals(0.12d, input.getVolatility(), delta);
        assertEquals(100d, input.getTimeToMaturity(), delta);
        assertEquals(PutCall.CALL, input.getPutCall());
        assertEquals(ExerciseType.EUROPEAN, input.getExerciseType());
        assertEquals(input.getRiskFreeRate() - input.getDividendRate(), input.getCostOfCarry(), delta);
    }

    @Test
    public void testToByteArray() {
        CalcRequest calcRequest = new CalcRequest("0005.hk", CalculatorType.GBSM,
                new EquityOptionInput(100, 90, 0.03, 0.3, 100, PutCall.CALL, ExerciseType.EUROPEAN, 0));
        assertNotNull(calcRequest.toByteArray());
    }
}
