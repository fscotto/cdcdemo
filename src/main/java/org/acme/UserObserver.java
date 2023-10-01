package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import org.jboss.logging.Logger;

@ApplicationScoped
public class UserObserver {
    private final Logger log = Logger.getLogger(UserObserver.class);

    public void onCreation(@Observes(during = TransactionPhase.AFTER_SUCCESS) UserCreated event) {
        log.info("Send event for user " + event);
    }

    public void onDelete(@Observes(during = TransactionPhase.AFTER_SUCCESS) UserDeleted event) {
        log.info("Send event for deletion user " + event);
    }

}
