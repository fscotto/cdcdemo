package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {
    private final Logger log = Logger.getLogger(UserService.class);

    private final Event<Suspendable> suspendableEventCollector;

    @Inject
    public UserService(Event<Suspendable> suspendableEventCollector) {
        this.suspendableEventCollector = suspendableEventCollector;
    }

    public Collection<UserDTO> getUsers() {
        return User.<User>findAll()
                .stream()
                .map(it -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setName(it.getName());
                    userDTO.setPassword(it.getPassword());
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void createUser(UserDTO user) {
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());

        UserCreated userCreated = new UserCreated();
        userCreated.setUsername(userEntity.getName());
        userCreated.setTimestamp(LocalDateTime.now());
        log.info("Creation event fired");
        suspendableEventCollector.fire(userCreated);

        User.persist(userEntity);
        log.info("Created user " + user.getName());
    }

    @Transactional
    public void deleteUser(Long id) {
        UserDeleted userDeleted = new UserDeleted();
        userDeleted.setId(id);
        userDeleted.setTimestamp(LocalDateTime.now());
        log.info("Delete event fired");
        suspendableEventCollector.fire(userDeleted);

        User.deleteById(id);
        log.info("Deleted user with id " + id);
    }

}
