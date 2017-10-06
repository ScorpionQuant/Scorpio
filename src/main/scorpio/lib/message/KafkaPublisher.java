package lib.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisher implements Publisher<Payload> {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaPublisher.class);

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Override
    public void publish(String topic, Payload payload) {
        LOG.info("publish to [{}] with payload {}", topic, payload);

        kafkaTemplate.send(topic, payload.toByteArray());
    }
}
