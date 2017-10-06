package app.pricer;

import app.pricer.service.CalcHandler;
import lib.calc.CalcRequest;
import lib.calc.CalcResponse;
import lib.message.KafkaPublisher;
import lib.message.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PricingScheduler implements Subscriber<CalcRequest> {
    private final static Logger LOG = LoggerFactory.getLogger(PricingScheduler.class);

    @Value("${kafka.topic.pricing.response}")
    private String kafkaPricingResponseTopic;

    @Autowired
    private KafkaPublisher messageChannel;

    @Autowired
    private CalcHandler calcHandler;

    private void publishResponse(CalcResponse response) {
        messageChannel.publish(kafkaPricingResponseTopic, response);
    }

    @Override
    @KafkaListener(topics = "${kafka.topic.pricing.request}")
    public void onMessage(byte[] bytes) {
        CalcRequest request = toObject(bytes);
        LOG.info("receive pricing request {} ", request);
        CalcResponse response = calcHandler.handle(request);

        LOG.info("publish pricing response {}", response);
        publishResponse(response);
    }
}
