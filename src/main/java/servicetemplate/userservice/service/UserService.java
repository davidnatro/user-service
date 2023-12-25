package servicetemplate.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import servicetemplate.userservice.data.dto.RegistrationDto;
import servicetemplate.userservice.data.model.UserModel;

public interface UserService {

  Page<UserModel> findAll(Pageable pageable);

  UserModel findById(Long id);

  UserModel findByUsername(String username);

  void createRegistrationRequest(RegistrationDto registrationDto);

  UserModel processRegistration(RegistrationDto registrationDto);
}
