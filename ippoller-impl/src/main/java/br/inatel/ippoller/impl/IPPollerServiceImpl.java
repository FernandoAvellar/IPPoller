package br.inatel.ippoller.impl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.ippoller.api.IPPollerService;
import br.inatel.ippoller.interfaces.IPPollerRemote;

@RequestScoped
public class IPPollerServiceImpl implements IPPollerService {

	@EJB(lookup = "java:app/ippoller-ejb-0.1-SNAPSHOT/IPPollerBean!br.inatel.ippoller.interfaces.IPPollerRemote")
	private IPPollerRemote ippollerBean;

	@Override
	public void startPoller(String ipNetworkAddress, String mask) {
		// criaFaixaDeHostsValidos(ipNetworkAddress, mask)
		// geraMensagensParaAFila()
	}

	@Override
	public boolean getStatus(String ipAddress) {
		return ippollerBean.getStatus(ipAddress);
	}

}
