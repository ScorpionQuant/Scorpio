package lib.message;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * All Objects which would be transited via Kafka message chanel should implement this interface
 */
public interface Payload extends Serializable {

    default byte[] toByteArray() {
        byte[] bytes = null;
        try(ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b)){
            o.writeObject(this);
            bytes = b.toByteArray();
        } catch (IOException e) {
            ExceptionUtils.printRootCauseStackTrace(e);
        }
        return bytes;
    }
}
