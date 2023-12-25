package servicetemplate.userservice.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JacksonSerializer<T> {

  protected static final ObjectMapper objectMapper = new ObjectMapper();
}
