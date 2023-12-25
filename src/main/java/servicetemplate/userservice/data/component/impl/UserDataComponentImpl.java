package servicetemplate.userservice.data.component.impl;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import servicetemplate.userservice.data.component.UserDataComponent;
import servicetemplate.userservice.data.entity.User;
import servicetemplate.userservice.data.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserDataComponentImpl implements UserDataComponent {

  private final UserRepository repository;

  @Override
  public Page<User> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public User findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not found"));
  }

  @Override
  public User findByUsername(String username) {
    return repository.findByUsername(username)
        .orElseThrow(() -> new NotFoundException("User not found"));
  }

  @Override
  public User create(User user) {
    if (repository.existsByUsername(user.getUsername())) {
      throw new IllegalArgumentException("Username already exists");
    }

    return repository.save(user);
  }
}
