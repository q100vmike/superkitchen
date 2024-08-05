package ru.mts.homework.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import lombok.extern.log4j.Log4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.homework.entity.Order;
import ru.mts.homework.kafka.KafkaProducer;
import ru.mts.homework.services.KitchenService;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Log4j
@RestController
public class KitchenController {

    private static final Logger LOG = LogManager.getLogger(KitchenController.class.getName());

    @Autowired
    private KitchenService kitchenService;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaTemplate<String, Order> orderKafkaTemplate;

    @PostMapping("/kafka")
    public void testKafka(@RequestBody String message) {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        orderKafkaTemplate.send("kitchen", order);
        //kafkaProducer.send(message);
        System.out.println("Sent message: " + message);
    }

    @PostMapping("/order")
    public String postOrder(Order order) {

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.MS");
        String date = ZonedDateTime.now().format(FORMATTER);

        LOG.debug("date: " + date);

        order.setCreated(Timestamp.valueOf(date));

        LOG.debug("Order created: " + order);
        kitchenService.save(order);

        String orderid = String.valueOf(order.getId());

        Map<String, Object> variables = new HashMap<>();
        variables.put("order", order);

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService=processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("superkitchen-process", variables);

        return "Заказ принят!";
    }

    @GetMapping("/hello-world")
    public String sayHello() {
        return "hello_world";
    }
}
