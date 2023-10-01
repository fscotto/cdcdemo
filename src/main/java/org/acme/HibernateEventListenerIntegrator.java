package org.acme;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.spi.BootstrapContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.jboss.logging.Logger;

@Startup
@ApplicationScoped
public class HibernateEventListenerIntegrator implements Integrator {
    private final Logger log = Logger.getLogger(HibernateEventListenerIntegrator.class);

    @Override
    public void integrate(Metadata metadata, BootstrapContext bootstrapContext, SessionFactoryImplementor sessionFactory) {
        log.info("Integrate registry");
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.appendListeners(EventType.POST_COMMIT_INSERT, UserEventListener.class);
        registry.appendListeners(EventType.POST_COMMIT_UPDATE, UserEventListener.class);
        registry.appendListeners(EventType.POST_COMMIT_DELETE, UserEventListener.class);
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactoryImplementor, SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {
    }

}
