package br.inatel.ippoller.beans;

import javax.ejb.EJB;

import br.inatel.ippoller.dao.IPStatusDAO;
import br.inatel.ippoller.interfaces.IPPollerLocal;
import br.inatel.ippoller.interfaces.IPPollerRemote;

public class IPPollerBean implements IPPollerLocal, IPPollerRemote {

	@EJB
	private IPStatusDAO ipstatusDAO;
	
	@Override
	public boolean getStatus(String ipAddress) {
		return ipstatusDAO.getStatus(ipAddress);
	}

	@Override
	public void insertHost(String hostIp, boolean status) {
		ipstatusDAO.insertHost(hostIp, status);
	}

}
