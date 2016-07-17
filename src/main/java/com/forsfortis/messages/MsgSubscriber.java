package com.forsfortis.messages;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.forsfortis.service.dbservice.ServiceFactory;
import com.forsfortis.util.ObjectInfo;

public class MsgSubscriber implements MessageListener {
	/*
	 * private static final Logger LOGGER = LoggerFactory
	 * .getLogger(Subscriber.class);
	 */

	private String clientId;
	private Connection connection;
	private Session session;
	private MessageConsumer messageConsumer;

	public void create(String clientId, String topicName) throws JMSException {
		this.clientId = clientId;

		// create a Connection Factory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

		// create a Connection
		connection = connectionFactory.createConnection();
		connection.setClientID(clientId);

		// create a Session
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// create the Topic from which messages will be received
		Topic topic = session.createTopic(topicName);

		// create a MessageConsumer for receiving messages
		messageConsumer = session.createConsumer(topic);
		messageConsumer.setMessageListener(this);

		// start the connection in order to receive messages
		connection.start();
	}

	public void closeConnection() throws JMSException {
		connection.close();
	}

	// message reciever
	public void getMessage() {
	
	}

	@Override
	public void onMessage(Message message) {
		// check if a message was received
		if (message instanceof ObjectMessage) {
			
			try {
				ObjectMessage objectMessage = (ObjectMessage)message;
				ObjectInfo infoObject = (ObjectInfo) objectMessage.getObject();
				System.out.println("message ------------>>>>>>>>>>>>"+infoObject.getDeviceType()+":"+infoObject.getTimestamp());
				ServiceFactory.getInstance().getObjectInfoService().insertObjectInfo(infoObject);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("no message recieved at the suscriber");
		}
	}
}
