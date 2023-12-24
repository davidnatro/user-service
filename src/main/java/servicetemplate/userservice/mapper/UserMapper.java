package servicetemplate.userservice.mapper;

import org.mapstruct.Mapper;
import servicetemplate.userservice.data.entity.User;
import servicetemplate.userservice.data.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserModel toModel(User user);
}
