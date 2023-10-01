package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener, PostCommitDeleteEventListener {
    private final Logger log = Logger.getLogger(UserEventListener.class);
    private final EventCollector eventCollector;

    @Inject
    public UserEventListener(EventCollector eventCollector) {
        this.eventCollector = eventCollector;
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        log.info("onPostInsert called");
        List<CollectEvent> events = eventCollector.stream(User.class.getCanonicalName())
                .filter(it -> it.getType() == CollectEvent.Type.CREATION)
                .collect(Collectors.toUnmodifiableList());

        log.info("fire " + events.size() + " events");
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        log.info("onPostUpdate called");
        List<CollectEvent> events = eventCollector.stream(User.class.getCanonicalName())
                .filter(it -> it.getType() == CollectEvent.Type.UPDATE)
                .collect(Collectors.toUnmodifiableList());

        log.info("fire " + events.size() + " events");
    }

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        log.info("onPostDelete called");
        List<CollectEvent> events = eventCollector.stream(User.class.getCanonicalName())
                .filter(it -> it.getType() == CollectEvent.Type.DELETE)
                .collect(Collectors.toUnmodifiableList());

        log.info("fire " + events.size() + " events");
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return true;
    }

    @Override
    public void onPostDeleteCommitFailed(PostDeleteEvent postDeleteEvent) {
        log.info("onPostDeleteCommitFailed called");
    }

    @Override
    public void onPostInsertCommitFailed(PostInsertEvent postInsertEvent) {
        log.info("onPostInsertCommitFailed called");
    }

    @Override
    public void onPostUpdateCommitFailed(PostUpdateEvent postUpdateEvent) {
        log.info("onPostUpdateCommitFailed called");
    }

}
