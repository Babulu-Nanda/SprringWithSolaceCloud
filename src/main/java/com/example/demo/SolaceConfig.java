package com.example.demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.solace.messaging.MessagingService;
import com.solace.messaging.config.SolaceProperties.AuthenticationProperties;
import com.solace.messaging.config.SolaceProperties.ServiceProperties;
import com.solace.messaging.config.SolaceProperties.TransportLayerProperties;
import com.solace.messaging.config.profile.ConfigurationProfile;
import com.solace.messaging.publisher.DirectMessagePublisher;
import com.solace.messaging.publisher.OutboundMessage;
import com.solace.messaging.publisher.OutboundMessageBuilder;
import com.solace.messaging.receiver.DirectMessageReceiver;
import com.solace.messaging.receiver.InboundMessage;
import com.solace.messaging.receiver.MessageReceiver.MessageHandler;
import com.solace.messaging.resources.Topic;
import com.solace.messaging.resources.TopicSubscription;



@Component
public class SolaceConfig {
/*
@Bean 
public DirectMessagePublisher getsolacePublish() {

		final Properties properties = new Properties();
	
	properties.setProperty(TransportLayerProperties.HOST,
			"tcps://mr-connection-lcryn5uu3kh.messaging.solace.cloud:55443");
	properties.setProperty(ServiceProperties.VPN_NAME, "my-first-service");
	properties.setProperty(AuthenticationProperties.SCHEME_BASIC_USER_NAME, "solace-cloud-client");
	properties.setProperty(AuthenticationProperties.SCHEME_BASIC_PASSWORD, "qhrf56rtsonr4sjtuq05o9jlss");
	final MessagingService messagingService = MessagingService.builder(ConfigurationProfile.V1)

			.fromProperties(properties).build()

			.connect();

	// create and start the publisher
	final DirectMessagePublisher publisher = messagingService.createDirectMessagePublisherBuilder().build()
			.start();
	return publisher;
	}
*/
}




