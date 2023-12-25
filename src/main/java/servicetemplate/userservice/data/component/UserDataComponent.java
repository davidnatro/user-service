package servicetemplate.userservice.data.component;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import servicetemplate.userservice.data.entity.User;

public interface UserDataComponent {

  Page<User> findAll(Pageable pageable);

  User findById(Long id);

  User findByUsername(String username);

  User create(User user);
}
