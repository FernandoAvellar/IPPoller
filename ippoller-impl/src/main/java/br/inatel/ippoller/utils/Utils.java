package br.inatel.ippoller.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.util.SubnetUtils;

public class Utils {

	private Utils() {
	}

	public static List<String> geraFaixaDeHostsValidos(String ipNetworkAddress, String cidrMask) {

		String cidrNotation = ipNetworkAddress + "/" + cidrMask;
		SubnetUtils subnetUtils = new SubnetUtils(cidrNotation);
		return Arrays.asList(subnetUtils.getInfo().getAllAddresses());
	}

	public static <T> List<List<T>> criaSubListas(List<T> listaPrincipal, final int tamanhoDasSubListas) {
		List<List<T>> listasSegmentadas = new ArrayList<List<T>>();
		final int tamanhoListaPrincipal = listaPrincipal.size();
		for (int i = 0; i < tamanhoListaPrincipal; i += tamanhoDasSubListas) {
			listasSegmentadas.add(new ArrayList<T>(
					listaPrincipal.subList(i, Math.min(tamanhoListaPrincipal, i + tamanhoDasSubListas))));
		}
		return listasSegmentadas;
	}
}
