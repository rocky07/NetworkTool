package com.forsfortis.messages;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.forsfortis.util.ObjectInfo;

import ch.qos.logback.classic.Logger;

//creates and starts the agent publisher where all agents put their message
public class MsgPublisher {
	/*private static final Logger LOGGER = LoggerFactory
            .getLogger(MsgPublisher.class);*/

    private String clientId;
    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

    
    public void create(String clientId, String topicName) throws JMSException {
        this.clientId = clientId;

        // create a Connection Factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_BROKER_URL);

        // create a Connection
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);

        // create a Session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create the Topic to which messages will be sent
        Topic topic = session.createTopic(topicName);

        // create a MessageProducer for sending messages
        messageProducer = session.createProducer(topic);
    }
    
    public void closeConnection() throws JMSException {
        connection.close();
    }
    
    public void sendMessage(ObjectInfo msg) throws JMSException{
    	//TODO send the message to mq
    	ObjectMessage objMessage=session.createObjectMessage();
    	objMessage.setObject(msg);
    	messageProducer.send(objMessage);
    	/*
    	TextMessage textMessage = this.session.createTextMessage(msg);
    	messageProducer.send(textMessage);*/
    }
}
