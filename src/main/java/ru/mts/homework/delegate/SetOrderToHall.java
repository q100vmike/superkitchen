package ru.mts.homework.delegate;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class SetOrderToHall implements JavaDelegate {

    private static final Logger LOG = LogManager.getLogger(SetOrderToHall.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOG.info("SetOrderToHall");
    }
}
