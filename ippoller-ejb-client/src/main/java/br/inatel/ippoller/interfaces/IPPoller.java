package br.inatel.ippoller.interfaces;

import java.util.List;

public interface IPPoller {

	boolean getStatus(String ipAddress);

	void insertHost(String hostIp, boolean status);
	
	void enviaMensagem(List<String> hostAddressesList);
}
