package servicetemplate.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import servicetemplate.userservice.data.component.UserDataComponent;
import servicetemplate.userservice.mapper.UserMapper;
import servicetemplate.userservice.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserDataComponent userDataComponent;
}
