package lib.message;

public interface Publisher<T> {

    void publish(String topic, T payload);
}
