package lib.calc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalcResponseTest {

    @Test
    public void testSerialization() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CalcResponse originResponse = new CalcResponse("0005.hk",
                new Valuation(100d, 0.3, 0.13, 9.58, 0.22, 6.25E-4, 9.44E-4, 2.32, 19.96));
        String responseStr = mapper.writeValueAsString(originResponse);
        assertNotNull(responseStr);
        CalcResponse convertedResponse = mapper.readValue(responseStr, CalcResponse.class);
        assertEquals(originResponse, convertedResponse);
    }

    @Test
    public void testToByteArray() {
        CalcResponse response = new CalcResponse("0005.hk",
                new Valuation(100d, 0.3, 0.13, 9.58, 0.22, 6.25E-4, 9.44E-4, 2.32, 19.96));
        assertNotNull(response);
    }
}
