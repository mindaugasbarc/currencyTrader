package com.learning.spring.user.application;

import com.learning.spring.user.domain.event.UserRegisteredEventListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    private static final String queueName = "user.registered";

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.addQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        return simpleMessageListenerContainer;
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter(UserRegisteredEventListener userRegisteredEventListener) {
        return new MessageListenerAdapter(userRegisteredEventListener, "handleUserRegisteredEvent");
    }
}
