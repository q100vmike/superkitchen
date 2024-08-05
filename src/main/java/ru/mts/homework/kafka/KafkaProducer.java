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


    private KafkaTemplate<String, Order> userKafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.userKafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        Map<String, String> record = new HashMap<>();
        record.put("message", message);
        LOG.info("messsage send: " + message);
        userKafkaTemplate.send("kitchen", "message", new Order());
    }
}
