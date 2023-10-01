package org.acme;

import jakarta.enterprise.context.RequestScoped;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RequestScoped
public class EventCollector {
    private final Map<String, Collection<CollectEvent>> cache;

    public EventCollector() {
        cache = new HashMap<>();
    }

    public void register(String type, CollectEvent event) {
        if (!cache.containsKey(type)) {
            cache.put(type, new ArrayList<>());
        }
        cache.get(type).add(event);
    }

    public Stream<CollectEvent> stream(String type) {
        return cache.get(type).stream();
    }

}
