package ru.mts.homework.delegate;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class CheckIngridients implements JavaDelegate {

    private static final Logger LOG = LogManager.getLogger(CheckIngridients.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Boolean isWin = false;
        delegateExecution.setVariable("isWin", isWin);
        LOG.debug("CheckIngridients");
    }
}
