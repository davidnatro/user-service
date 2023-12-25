package servicetemplate.userservice.util;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import servicetemplate.userservice.data.entity.User;

public final class KeycloakUtils {

  private KeycloakUtils() { }

  public static UserRepresentation formUser(User user) {
    UserRepresentation userRepresentation = new UserRepresentation();
    userRepresentation.setUsername(user.getUsername());
    userRepresentation.setEmail(user.getEmail());
    userRepresentation.setEmailVerified(false);
    userRepresentation.setEnabled(false);
    return userRepresentation;
  }

  public static CredentialRepresentation formCredentials(String password) {
    CredentialRepresentation credential = new CredentialRepresentation();
    credential.setType(CredentialRepresentation.PASSWORD);
    credential.setValue(password);
    credential.setTemporary(false);
    return credential;
  }
}
