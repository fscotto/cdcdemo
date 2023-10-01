package org.acme;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserCreated implements CollectEvent {
    private String username;
    private LocalDateTime timestamp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public CollectEvent.Type getType() {
        return Type.CREATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreated that = (UserCreated) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getTimestamp());
    }

    @Override
    public String toString() {
        return "UserCreated{" +
                "username='" + username + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
