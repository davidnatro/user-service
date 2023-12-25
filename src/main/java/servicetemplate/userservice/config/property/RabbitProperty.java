package servicetemplate.userservice.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitProperty {

  private String host;
  private String username;
  private String password;
  private Integer port;
  private String virtualHost;
  private String exchange;
  private Integer prefetchCount;
  private Integer consumersPerQueue;
  private String registrationRequestsQueue;
  private String registrationRequestsRoutingKey;
}
