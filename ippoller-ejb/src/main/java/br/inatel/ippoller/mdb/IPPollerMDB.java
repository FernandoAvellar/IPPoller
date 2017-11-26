package br.inatel.ippoller.mdb;

import java.util.ArrayList;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.ippoller.dao.IPStatusDAO;
import br.inatel.ippoller.utils.PingUtility;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ippollerqueue") })
public class IPPollerMDB implements MessageListener {

	@EJB
	private IPStatusDAO ipStatusDAO;

	@Override
	@SuppressWarnings("unchecked")
	public void onMessage(Message message) {
		System.out.println("######################## Inicializando Processamento de até 10 hosts");

		if (message instanceof ObjectMessage) {
			Object object = null;
			try {
				object = ((ObjectMessage) message).getObject();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			ArrayList<String> hostAddresses = (ArrayList<String>) object;

			for (String hostIp : hostAddresses) {
				boolean hostStatus = PingUtility.execPing(hostIp);
				ipStatusDAO.insertHost(hostIp, hostStatus);
			}

			System.out.println("######################## Finalizando Processamento");
		}
	}
}
