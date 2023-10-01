package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class UserObserver {
    private final Logger log = Logger.getLogger(UserObserver.class);

    private final EventCollector eventCollector;

    @Inject
    public UserObserver(EventCollector eventCollector) {
        this.eventCollector = eventCollector;
    }

    public void onCreation(@Observes(during = TransactionPhase.AFTER_SUCCESS) UserCreated event) {
        log.info("Arrived event for user " + event.getUsername());
        eventCollector.register(User.class.getCanonicalName(), event);
    }

    public void onDelete(@Observes(during = TransactionPhase.AFTER_SUCCESS) UserDeleted event) {
        log.info("Arrived event for deletion user " + event.getId());
        eventCollector.register(User.class.getCanonicalName(), event);
    }

}
