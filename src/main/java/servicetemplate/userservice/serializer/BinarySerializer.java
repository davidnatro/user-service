package servicetemplate.userservice.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;

public abstract class BinarySerializer<T> extends JacksonSerializer<T> {

  public abstract byte[] serialize(T object) throws IOException;

  public abstract T deserialize(byte[] object) throws IOException;
}
