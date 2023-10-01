package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import org.jboss.logging.Logger;

@ApplicationScoped
public class UserObserver {
    private final Logger log = Logger.getLogger(UserObserver.class);

    public void onCreation(@Observes(during = TransactionPhase.AFTER_SUCCESS) UserCreated event) {
        log.info("Arrived event for user " + event.getUsername());
    }

    public void onDelete(@Observes(during = TransactionPhase.AFTER_SUCCESS) UserDeleted event) {
        log.info("Arrived event for deletion user " + event.getId());
    }

}
