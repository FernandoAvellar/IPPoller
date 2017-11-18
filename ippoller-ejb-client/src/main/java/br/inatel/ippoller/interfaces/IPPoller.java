package br.inatel.ippoller.interfaces;

public interface IPPoller {
	
	boolean getStatus(String ipAddress);

	void insertHost(String hostIp, boolean status);

}
