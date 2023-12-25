package servicetemplate.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import servicetemplate.userservice.data.component.UserDataComponent;
import servicetemplate.userservice.data.dto.RegistrationDto;
import servicetemplate.userservice.data.entity.User;
import servicetemplate.userservice.data.model.UserModel;
import servicetemplate.userservice.mapper.UserMapper;
import servicetemplate.userservice.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserDataComponent userDataComponent;

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

  }

  @Override
  public UserModel processRegistration(RegistrationDto registrationDto) {
    return null;
  }
}
