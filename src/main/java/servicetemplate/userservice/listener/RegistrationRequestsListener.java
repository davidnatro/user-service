package servicetemplate.userservice.listener;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import servicetemplate.userservice.data.dto.RegistrationDto;
import servicetemplate.userservice.serializer.BinarySerializer;
import servicetemplate.userservice.service.UserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationRequestsListener implements ChannelAwareMessageListener {

  private final UserService userService;
  private final BinarySerializer<RegistrationDto> registrationRequestSerializer;

  @Override
  public void onMessage(Message message, Channel channel) throws Exception {
    try {
      RegistrationDto registrationDto = registrationRequestSerializer.deserialize(
          message.getBody());
      userService.processRegistration(registrationDto);
      channel.basicAck(message.getMessageProperties()
                           .getDeliveryTag(), false);
      log.info("Processed registration request for user '{}'", registrationDto.username());
    } catch (Exception exception) {
      channel.basicReject(message.getMessageProperties()
                              .getDeliveryTag(), false);
      log.error("Failed to process registration request from queue -> message: '{}'",
                exception.getMessage());
    }
  }
}
