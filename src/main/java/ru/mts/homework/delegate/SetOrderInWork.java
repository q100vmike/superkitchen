package ru.mts.homework.delegate;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.BooleanValue;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.homework.controller.KitchenController;
import ru.mts.homework.entity.Order;

@Log4j
@Component
public class SetOrderInWork implements JavaDelegate {

    private static final Logger LOG = LogManager.getLogger(SetOrderInWork.class.getName());

    @Autowired
    private KafkaTemplate<String, Order> orderKafkaTemplate;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Order order = (Order) delegateExecution.getVariable("order");

        //меняем статус и отсылаем в kafka
        order.setStatus("In Work");
        orderKafkaTemplate.send("kitchen", order);

        LOG.debug("name: " + order.getName());

    }
}
