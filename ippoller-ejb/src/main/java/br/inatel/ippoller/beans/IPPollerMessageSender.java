package br.inatel.ippoller.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

@Stateless
public class IPPollerMessageSender {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/ippollerqueue")
	private Queue queue;

	public void sendMessage(List<String> hostAddressesList) {
		try (Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession();
				MessageProducer producer = session.createProducer(queue);) {
			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject((Serializable) hostAddressesList);
			producer.send(objectMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
