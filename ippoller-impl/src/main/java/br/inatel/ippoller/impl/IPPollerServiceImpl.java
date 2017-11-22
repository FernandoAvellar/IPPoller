package br.inatel.ippoller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<String> addresses = geraFaixaDeHostsValidos(ipNetworkAddress, cidrMask);
		geraMensagensParaAFila(addresses);
	}

	@Override
	public boolean getStatus(String ipAddress) {
		return ippollerBean.getStatus(ipAddress);
	}

	private static void geraMensagensParaAFila(List<String> addresses) {

		List<List<String>> subMessages = chopped(addresses, 10);

		for (List<String> list : subMessages) {
			//TODO: Enviar mensagem para a fila apos criar a fila.
			System.out.println(list);
		}
	}

	private static List<String> geraFaixaDeHostsValidos(String ipNetworkAddress, String cidrMask) {

		String cidrNotation = ipNetworkAddress + "/" + cidrMask;
		SubnetUtils subnetUtils = new SubnetUtils(cidrNotation);
		return Arrays.asList(subnetUtils.getInfo().getAllAddresses());
	}

	public static <T> List<List<T>> chopped(List<T> list, final int L) {
		List<List<T>> parts = new ArrayList<List<T>>();
		final int N = list.size();
		for (int i = 0; i < N; i += L) {
			parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
		}
		return parts;
	}

	public static void main(String[] args) {
		new IPPollerServiceImpl().startPoller("192.168.1.0", "26");
	}
}
