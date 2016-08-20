package com.forsfortis.messages;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.forsfortis.service.dbservice.ServiceFactory;
import com.forsfortis.util.ApplicationConstants;
import com.forsfortis.util.ObjectInfo;
import com.forsfortis.util.threshold.ThresholdMatcher;
import com.forsfortis.util.threshold.ThresholdMatcherFactory;

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
			ObjectMessage objectMessage = (ObjectMessage)message;
			if(this.clientId.equals(ApplicationConstants.APPLICATION_DB_SUBSCRIBER_CLIENT_ID)){
			try {
				ObjectInfo infoObject = (ObjectInfo) objectMessage.getObject();
				System.out.println("message ------------>>>>>>>>>>>>"+infoObject.getDeviceType()+":"+infoObject.getTimestamp());
				ServiceFactory.getInstance().getObjectInfoService().insertObjectInfo(infoObject);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{// check for threshold breach to generate incidents
				try {
					ObjectInfo infoObject = (ObjectInfo) objectMessage.getObject();
					ThresholdMatcher matcher = new ThresholdMatcherFactory().getMatcher(infoObject.getDeviceType());
					matcher.match(infoObject);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("no message recieved at the suscriber");
		}
	}
}
