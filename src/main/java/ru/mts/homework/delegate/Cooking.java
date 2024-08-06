package ru.mts.homework.delegate;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.homework.entity.Order;

@Log4j
@Component
public class Cooking implements JavaDelegate {

    @Autowired
    private KafkaTemplate<String, Order> orderKafkaTemplate;

    private static final Logger LOG = LogManager.getLogger(Cooking.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Order order = (Order) delegateExecution.getVariable("order");

        //меняем статус и отсылаем в kafka
        order.setStatus("COOKING");
        orderKafkaTemplate.send("kitchen", order);

        LOG.debug("Cooking order: " + order.getName());
    }
}
