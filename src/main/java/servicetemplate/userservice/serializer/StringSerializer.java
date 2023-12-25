package servicetemplate.userservice.serializer;

import java.io.IOException;

public abstract class StringSerializer<T> extends JacksonSerializer<T> {

  public abstract String serialize(T object) throws IOException;

  public abstract T deserialize(String object) throws IOException;
}
