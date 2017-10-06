package org.scorpion.message;

public interface Publisher<T> {

    void publish(String topic, T payload);
}
