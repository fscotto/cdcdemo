package org.acme;

import java.io.Serializable;

public interface CollectEvent extends Serializable {
    Type getType();

    enum Type {
        CREATION, UPDATE, DELETE;
    }
}
