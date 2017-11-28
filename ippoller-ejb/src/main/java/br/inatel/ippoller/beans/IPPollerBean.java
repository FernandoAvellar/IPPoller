package br.inatel.ippoller.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.inatel.ippoller.dao.IPStatusDAO;
import br.inatel.ippoller.interfaces.IPPollerLocal;
import br.inatel.ippoller.interfaces.IPPollerRemote;

@Stateless
@Remote(IPPollerRemote.class)
@Local(IPPollerLocal.class)
public class IPPollerBean implements IPPollerLocal, IPPollerRemote {

	@EJB
	private IPStatusDAO ipstatusDAO;

	@EJB
	private IPPollerMessageSender messageSender;

	@Override
	public boolean getStatus(String ipAddress) {
		return ipstatusDAO.getStatus(ipAddress);
	}

	@Override
	public void insertHost(String hostIp, boolean status) {
		ipstatusDAO.insertHost(hostIp, status);
	}

	@Override
	public void enviaMensagem(List<String> hostAddressesList) {
		messageSender.sendMessage(hostAddressesList);
	}
}
