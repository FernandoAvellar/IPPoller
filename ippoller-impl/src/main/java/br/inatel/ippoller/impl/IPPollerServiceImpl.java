package br.inatel.ippoller.impl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import org.apache.commons.net.util.SubnetUtils;

import br.inatel.ippoller.api.IPPollerService;
import br.inatel.ippoller.interfaces.IPPollerRemote;

@RequestScoped
public class IPPollerServiceImpl implements IPPollerService {

	@EJB(lookup = "java:app/ippoller-ejb-0.1-SNAPSHOT/IPPollerBean!br.inatel.ippoller.interfaces.IPPollerRemote")
	private IPPollerRemote ippollerBean;

	@Override
	public void startPoller(String ipNetworkAddress, String cidrMask) {
		geraFaixaDeHostsValidos(ipNetworkAddress, cidrMask);
		// geraMensagensParaAFila()
	}

	@Override
	public boolean getStatus(String ipAddress) {
		return ippollerBean.getStatus(ipAddress);
	}

	private static void geraFaixaDeHostsValidos(String ipNetworkAddress, String cidrMask) {

		String cidrNotation = ipNetworkAddress + "/" + cidrMask;
		SubnetUtils subnetUtils = new SubnetUtils(cidrNotation);
		String[] addresses = subnetUtils.getInfo().getAllAddresses();
		for (String host : addresses) {
			System.out.println(host);

		}

	}

	public static void main(String[] args) {
		geraFaixaDeHostsValidos("192.168.1.0", "24");
	}
}
