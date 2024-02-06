package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.solace.messaging.resources.TopicSubscription;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPFactory;
import com.solacesystems.jcsmp.JCSMPProperties;
import com.solacesystems.jcsmp.JCSMPSession;
import com.solacesystems.jcsmp.JCSMPStreamingPublishEventHandler;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jcsmp.Topic;
import com.solacesystems.jcsmp.XMLMessageProducer;

//import com.solacesystems.jcsmp.*;


@Service
public class SolaceDirectService {
	@Autowired
	SolaceConfig config;

	public void sendMessageToTopic(String topicName, String messageText) {
		try {
			
			/*DirectMessagePublisher publisher=	config.getsolacePublish();

			System.err.println("Before Publish");
			// publish a message
			publisher.publish(messageText, Topic.of(topicName));
			System.out.println("Successfully Published to This topic:+topic Name is::::" + topicName);
		
			
			publisher.setPublishFailureListener(e -> {    
				System.out.println("### FAILED PUBLISH " + e);  
				});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
			
			
			final JCSMPProperties properties = new JCSMPProperties();
			/*properties.setProperty(JCSMPProperties.HOST, "tcps://mr-connection-lcryn5uu3kh.messaging.solace.cloud:55443");
			properties.setProperty(JCSMPProperties.USERNAME, "solace-cloud-client");
			properties.setProperty(JCSMPProperties.PASSWORD, "qhrf56rtsonr4sjtuq05o9jlss");
			properties.setProperty(JCSMPProperties.VPN_NAME, "my-first-service");
			
			
			*/
			
			properties.setProperty(JCSMPProperties.HOST, "tcp://51.112.63.158:55555");
			properties.setProperty(JCSMPProperties.USERNAME, "devuser-matm");
			properties.setProperty(JCSMPProperties.PASSWORD, "Matm@8895");
			properties.setProperty(JCSMPProperties.VPN_NAME, "default");
			System.out.println("Before Connection Establish");
			final JCSMPSession session = JCSMPFactory.onlyInstance().createSession(properties);

			System.out.println("Successfully Connect to Solace Cloud");
			session.connect();
			System.out.println("Session created");
			
			final Topic topic = JCSMPFactory.onlyInstance().createTopic(topicName);

		        // Create a producer
		        XMLMessageProducer producer = session.getMessageProducer(new JCSMPStreamingPublishEventHandler() {
		            @Override
		            public void handleError(String messageId, JCSMPException cause, long timestamp) {
		                System.err.println("Error sending message: " + cause);
		            }

		            @Override
		            public void responseReceived(String messageId) {
		                System.out.println("Message sent successfully. Message ID: " + messageId);
		            }
		        });

		        // Create a text message
		        TextMessage msg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
		        String text = "Hello, Solace!";
		        msg.setText(text);

		        System.out.println("Message Published");
		        
		        // Publish the message to the topic
		        producer.send(msg, topic);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
	

			        
		
	}

	public void subscribeToTopic(String topicName) {
	
		final Properties properties = new Properties();
		properties.setProperty(TransportLayerProperties.HOST,
				"tcps://mr-connection-lcryn5uu3kh.messaging.solace.cloud:55443");
		properties.setProperty(ServiceProperties.VPN_NAME, "my-first-service");
		properties.setProperty(AuthenticationProperties.SCHEME_BASIC_USER_NAME, "solace-cloud-client");
		properties.setProperty(AuthenticationProperties.SCHEME_BASIC_PASSWORD, "qhrf56rtsonr4sjtuq05o9jlss");
		final MessagingService messagingService = MessagingService.builder(ConfigurationProfile.V1)

				.fromProperties(properties).build()

				.connect();

		// subscribe a message
		 DirectMessageReceiver receiver = messagingService.createDirectMessageReceiverBuilder()
				.withSubscriptions(TopicSubscription.of(topicName)).build().start();
		
		 System.out.println("=============");
		 final MessageHandler messageHandler = (message) -> {       // Interface for the listener of message handlers for inbound messages.
			 byte[] bytes = message.getPayloadAsBytes();                // Do something with a message, i.e access raw payload.
			 };
			 receiver.receiveAsync(messageHandler);                     // This method represents a push-based non-blocking interface. 
			                  
	
			 System.out.println("-----------------");
		
	
		
		/*try {
		  // Create a JCSMP session
        JCSMPProperties properties = new JCSMPProperties();
        properties.setProperty(JCSMPProperties.HOST, "tcps://mr-connection-lcryn5uu3kh.messaging.solace.cloud:55443");
        properties.setProperty(JCSMPProperties.USERNAME, "solace-cloud-client");
        properties.setProperty(JCSMPProperties.PASSWORD, "qhrf56rtsonr4sjtuq05o9jlss");

        JCSMPSession session = JCSMPFactory.onlyInstance().createSession(properties);

        // Create a topic
       
        Topic topic = JCSMPFactory.onlyInstance().createTopic(topicName);

        // Create a message consumer
        XMLMessageConsumer consumer = session.getMessageConsumer(new XMLMessageListener() {
            @Override
            public void onReceive(BytesXMLMessage message) {
                System.out.println("Received message: " + new String(message.toString()));
            }

            @Override
            public void onException(JCSMPException e) {
                e.printStackTrace();
            }
        });

        // Subscribe to the topic
        session.addSubscription(topic);

        // Connect and start the consumer
        session.connect();
        consumer.start();

        // Use CountDownLatch or other mechanisms to keep the program running
        try {
            Thread.sleep(10000); // Adjust as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the session and consumer
        consumer.close();
		

		 

		

	}
		catch(Exception e) {
			}
		}*/
	}

	public void anotherWay(String string, String messageBody) {
	/*	 try {  // Solace broker connection information
		        String host = "tcps://mr-connection-lcryn5uu3kh.messaging.solace.cloud:55443";
		        String username = "solace-cloud-client";
		        String password = "qhrf56rtsonr4sjtuq05o9jlss";
		        String vpnName = "my-first-service";

		        // Solace session properties
		        JCSMPProperties properties = new JCSMPProperties();
		        properties.setProperty(JCSMPProperties.HOST, host);
		        properties.setProperty(JCSMPProperties.USERNAME, username);
		        properties.setProperty(JCSMPProperties.PASSWORD, password);
		        properties.setProperty(JCSMPProperties.VPN_NAME, vpnName);

		        // Create a JCSMP session
		        JCSMPSession session = JCSMPFactory.onlyInstance().createSession(properties);

		        // Connect to the Solace message broker
		        session.connect();
		   
		        System.out.println("Connected to Solace PubSub+ broker.");


		        // Create a topic
		        String topicString = "your/topic";
		        Topic topic = JCSMPFactory.onlyInstance().createTopic(topicString);
		     // After creating XMLMessageProducer
		        System.out.println("XMLMessageProducer created successfully.");
		        // Create a message producer with a custom event handler
		        @SuppressWarnings("deprecation")
				XMLMessageProducer producer = session.getMessageProducer(new JCSMPStreamingPublishEventHandler() 
		        {
		           
		        	@Override
		            public void responseReceived(String messageId) {
		                System.out.println("Published message with ID: " + messageId);
		            }

		            @Override
		            public void handleError(String messageId, JCSMPException e, long timestamp) {
		                System.err.println("Error publishing message: " + messageId + " - " + e);
		            }
		        });

		        // Create a message
		        TextMessage message = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
		        message.setText("Hello, Solace!");

		        // Publish the message
		        producer.send(message, topic);

		        // Keep the program running to receive messages
		        try {
		            Thread.sleep(5000); // Sleep for 5 seconds to allow for the response
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
  
	        
		
	}
		 catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	*/
	
	}
	
}
