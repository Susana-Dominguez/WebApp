package com.example.webapp;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {
    private static String brokerUrl = "tcp://localhost:61616";

    public void sendMessage(String messageText) {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TestQueue");

            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(messageText);
            producer.send(message);

            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
