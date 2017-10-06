package app.web.pricing;

import lib.calc.CalcRequest;
import lib.calc.CalcResponse;
import lib.message.KafkaPublisher;
import lib.message.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PricingService implements Subscriber<CalcResponse> {
    private final static Logger LOG = LoggerFactory.getLogger(PricingService.class);

    @Value("${kafka.topic.pricing.request}")
    private String kafkaPricingRequestTopic;
    @Value("${ws.queue.valuation}")
    private String wsQueueValuation;

    @Autowired
    private KafkaPublisher messageChannel;

    @Autowired
    private SimpMessagingTemplate template;

    public void publishPricingRequest(CalcRequest request) {
        messageChannel.publish(kafkaPricingRequestTopic, request);
    }

    @Override
    @KafkaListener(topics = "${kafka.topic.pricing.response}")
    public void onMessage(byte[] bytes) {
        CalcResponse response = toObject(bytes);
        LOG.info("receive pricing response {}", response);

        template.convertAndSend(wsQueueValuation, response);
    }
}