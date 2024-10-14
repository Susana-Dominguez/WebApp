package com.example.webapp;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageListener implements Runnable {
    private static String brokerUrl = "tcp://localhost:61616";

    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TestQueue");

            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new javax.jms.MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (message instanceof TextMessage) {
                        try {
                            String text = ((TextMessage) message).getText();
                            System.out.println("Received: " + text);
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
