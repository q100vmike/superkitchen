package ru.mts.homework.kafka;

import lombok.extern.log4j.Log4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mts.homework.entity.Order;

import java.util.HashMap;
import java.util.Map;

@Log4j
@Service
public class KafkaProducer {

    private static final Logger LOG = LogManager.getLogger(KafkaProducer.class.getName());

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        Map<String, String> record = new HashMap<>();
        record.put("message", message);
        LOG.info("messsage send: " + message);
        kafkaTemplate.send("kitchen", "message", message);
    }
}
