package org.acme;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserDeleted implements CollectEvent {
    private long id;
    private LocalDateTime timestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public CollectEvent.Type getType() {
        return Type.DELETE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDeleted that = (UserDeleted) o;
        return getId() == that.getId() && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTimestamp());
    }

    @Override
    public String toString() {
        return "UserDeleted{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }

}
