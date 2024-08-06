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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.homework.entity.Order;

import java.util.Random;

@Log4j
@Component
public class CheckIngridients implements JavaDelegate {

    @Autowired
    private KafkaTemplate<String, Order> orderKafkaTemplate;

    private static final Logger LOG = LogManager.getLogger(CheckIngridients.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
/*
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService=processEngine.getRuntimeService();
        BooleanValue boolWin = Variables.booleanValue(true);
*/

        Order order = (Order) delegateExecution.getVariable("order");

        //проверка наличия ингридиентов
        Thread.sleep(5000);
        Random random = new Random();
        boolean isCancel = random.nextBoolean();

        delegateExecution.setVariable("isCancel", isCancel);

        LOG.debug("name: " + order.getName() + " isCancel=" + isCancel);
    }
}
