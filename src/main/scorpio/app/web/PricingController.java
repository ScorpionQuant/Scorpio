package app.web;

import lib.calc.CalcRequest;
import app.web.pricing.PricingService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class PricingController {
    private static final Logger LOG = LoggerFactory.getLogger(PricingController.class);

    @Autowired
    private PricingService pricingService;

    @MessageMapping("pricing")
    public String handlePricingRequest(@Headers Map<String, Object> headers, @Payload CalcRequest pricingRequest,
                                       String message) {
        String sessionId = SimpMessageHeaderAccessor.getSessionId(headers);
        if (StringUtils.isEmpty(sessionId))  {
            throw new IllegalArgumentException("no web socket session found, ignoring the request");
        }

        LOG.info("receive pricing request: " + message);
        pricingService.publishPricingRequest(pricingRequest);
        return "Pricing Request --> Received";
    }
}
