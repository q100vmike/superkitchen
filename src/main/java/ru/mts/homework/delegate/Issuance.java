package ru.mts.homework.delegate;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class Issuance implements JavaDelegate {

    private static final Logger LOG = LogManager.getLogger(Issuance.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOG.info("Issuance started");

        Boolean isTakeAway = true;
        delegateExecution.setVariable("isTakeAway", isTakeAway);
    }
}
