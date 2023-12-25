package servicetemplate.userservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory.CacheMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import servicetemplate.userservice.config.property.RabbitProperty;
import servicetemplate.userservice.listener.RegistrationRequestsListener;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

  private final RabbitProperty property;

  @Bean
  public DirectExchange directExchange() {
    return ExchangeBuilder.directExchange(property.getExchange())
        .durable(true)
        .build();
  }

  @Bean
  public Queue registrationRequestsQueue() {
    return QueueBuilder.durable(property.getRegistrationRequestsQueue())
        .deadLetterRoutingKey(property.getRegistrationRequestsQueue() + "-dlq")
        .deadLetterExchange(property.getExchange())
        .build();
  }

  @Bean
  public Queue registrationRequestsDeadLetterQueue() {
    return QueueBuilder.durable(property.getRegistrationRequestsQueue() + "-dlq")
        .build();
  }

  @Bean
  public Binding registrationRequestsQueueBinding(DirectExchange directExchange,
                                                  Queue registrationRequestsQueue) {
    return BindingBuilder.bind(registrationRequestsQueue)
        .to(directExchange)
        .with(property.getRegistrationRequestsQueue());
  }

  @Bean
  public Binding registrationRequestsQueueDeadLetterBinding(DirectExchange directExchange,
                                                            Queue registrationRequestsDeadLetterQueue) {
    return BindingBuilder.bind(registrationRequestsDeadLetterQueue)
        .to(directExchange)
        .with(property.getRegistrationRequestsQueue() + "-dlq");
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setHost(property.getHost());
    connectionFactory.setUsername(property.getUsername());
    connectionFactory.setPassword(property.getPassword());
    connectionFactory.setPort(property.getPort());
    connectionFactory.setVirtualHost(property.getVirtualHost());
    connectionFactory.setCacheMode(CacheMode.CHANNEL);
    return connectionFactory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }

  @Bean
  public DirectMessageListenerContainer registrationRequestsQueueListenerContainer(
      ConnectionFactory connectionFactory, Queue registrationRequestsQueue,
      RegistrationRequestsListener listener) {
    DirectMessageListenerContainer container = new DirectMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addQueues(registrationRequestsQueue);
    container.setMessageListener(listener);
    container.setConsumersPerQueue(property.getConsumersPerQueue());
    container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    container.setPrefetchCount(property.getPrefetchCount());
    return container;
  }
}
