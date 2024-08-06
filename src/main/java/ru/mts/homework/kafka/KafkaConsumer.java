package ru.mts.homework.kafka;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mts.homework.delegate.Cooking;
import ru.mts.homework.entity.Order;
import ru.mts.homework.services.KitchenService;

@Log4j
@Service
public class KafkaConsumer {

    private static final Logger LOG = LogManager.getLogger(KafkaConsumer.class.getName());

    @Autowired
    private KitchenService kitchenService;

    @KafkaListener(topics = "kitchen", groupId = "superkitchen"
            , containerFactory = "orderKafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, Order> record) {
        Order order = record.value();
        kitchenService.save(order);
        LOG.info("Recive order: " + order.getId());
    }
}

