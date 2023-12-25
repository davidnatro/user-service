package servicetemplate.userservice.service.impl;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicetemplate.userservice.config.property.RabbitProperty;
import servicetemplate.userservice.data.component.UserDataComponent;
import servicetemplate.userservice.data.dto.RegistrationDto;
import servicetemplate.userservice.data.entity.User;
import servicetemplate.userservice.data.model.UserModel;
import servicetemplate.userservice.mapper.UserMapper;
import servicetemplate.userservice.serializer.BinarySerializer;
import servicetemplate.userservice.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final Keycloak keycloak;
  private final UserMapper userMapper;
  private final RabbitProperty rabbitProperty;
  private final RabbitTemplate rabbitTemplate;
  private final UserDataComponent userDataComponent;
  private final BinarySerializer<RegistrationDto> registrationRequestSerializer;

  @Override
  public Page<UserModel> findAll(Pageable pageable) {
    return userDataComponent.findAll(pageable)
        .map(userMapper::toModel);
  }

  @Override
  public UserModel findById(Long id) {
    return userMapper.toModel(userDataComponent.findById(id));
  }

  @Override
  public UserModel findByUsername(String username) {
    return userMapper.toModel(userDataComponent.findByUsername(username));
  }

  @Override
  public void createRegistrationRequest(RegistrationDto registrationDto) {
    try {
      byte[] request = registrationRequestSerializer.serialize(registrationDto);
      Message message = new Message(request);
      rabbitTemplate.send(rabbitProperty.getExchange(),
                          rabbitProperty.getRegistrationRequestsQueue(), message);
      log.info("Registration request for user '{}' sent to queue!", registrationDto.username());
    } catch (IOException exception) {
      log.error("Failed to serialize registration request -> message: '{}'",
                exception.getMessage());
      throw new SerializationFailedException("Failed to serialize registration request!");
    } catch (Exception exception) {
      log.error("Failed to send registration to queue request -> message: '{}'",
                exception.getMessage());
    }
  }

  @Override
  @Transactional
  public void processRegistration(RegistrationDto registrationDto) {
    User user = userDataComponent.create(userMapper.toEntity(registrationDto));
  }
}
