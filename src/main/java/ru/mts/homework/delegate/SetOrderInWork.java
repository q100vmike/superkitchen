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
import org.springframework.stereotype.Component;
import ru.mts.homework.controller.KitchenController;
import ru.mts.homework.entity.Order;

@Log4j
@Component
public class SetOrderInWork implements JavaDelegate {

    private static final Logger LOG = LogManager.getLogger(SetOrderInWork.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Order order = (Order) delegateExecution.getVariable("order");

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService=processEngine.getRuntimeService();

        BooleanValue boolWin = Variables.booleanValue(true);
        runtimeService.setVariable(delegateExecution.getId(), "isWin", boolWin);
        Boolean isWin = true;
        delegateExecution.setVariable("isWin", isWin);
        LOG.debug("name: " + order.getName());
    }
}
