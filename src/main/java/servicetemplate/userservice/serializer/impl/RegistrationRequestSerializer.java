package servicetemplate.userservice.serializer.impl;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import org.springframework.stereotype.Component;
import servicetemplate.userservice.data.dto.RegistrationDto;
import servicetemplate.userservice.serializer.BinarySerializer;

@Component
public class RegistrationRequestSerializer extends BinarySerializer<RegistrationDto> {

  private final ObjectWriter writer = objectMapper.writerFor(RegistrationDto.class);
  private final ObjectReader reader = objectMapper.readerFor(RegistrationDto.class);

  @Override
  public byte[] serialize(RegistrationDto object) throws IOException {
    return writer.writeValueAsBytes(object);
  }

  @Override
  public RegistrationDto deserialize(byte[] object) throws IOException {
    return reader.readValue(object);
  }
}
