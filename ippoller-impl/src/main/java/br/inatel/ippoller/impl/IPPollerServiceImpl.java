package br.inatel.ippoller.impl;

import static br.inatel.ippoller.utils.Utils.criaSubListas;
import static br.inatel.ippoller.utils.Utils.geraFaixaDeHostsValidos;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.ippoller.api.IPPollerService;
import br.inatel.ippoller.interfaces.IPPollerRemote;

@RequestScoped
public class IPPollerServiceImpl implements IPPollerService {

	@EJB(lookup = "java:app/ippoller-ejb-0.1-SNAPSHOT/IPPollerBean!br.inatel.ippoller.interfaces.IPPollerRemote")
	private IPPollerRemote ippollerBean;

	@Override
	public void startPoller(String ipNetworkAddress, String cidrMask) {
		List<String> addresses = geraFaixaDeHostsValidos(ipNetworkAddress, cidrMask);

		List<List<String>> enderecosDivididos = criaSubListas(addresses, 10);

		for (List<String> mensagemParcial : enderecosDivididos) {
			ippollerBean.enviaMensagem(mensagemParcial);
		}
	}

	@Override
	public boolean getStatus(String ipAddress) {
		return ippollerBean.getStatus(ipAddress);
	}
	
	public static void main(String[] args) {
		System.out.println(new IPPollerServiceImpl().getStatus("146.250.130.5"));
	}
}
