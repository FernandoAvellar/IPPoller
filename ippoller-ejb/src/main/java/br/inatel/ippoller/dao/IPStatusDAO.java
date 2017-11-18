package br.inatel.ippoller.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.ippoller.entities.HostEntity;

@Stateless
public class IPStatusDAO {

	@PersistenceContext(unitName = "ippoller")
	private EntityManager em;

	public boolean getStatus(String ipAddress) {
		HostEntity entity = em.find(HostEntity.class, ipAddress);
		if (entity == null) {
			return false;
		}
		return entity.getStatus();
	}

	public void insertHost(String hostIp, boolean status) {
		HostEntity hostEntity = new HostEntity();
		hostEntity.setHost(hostIp);
		hostEntity.setStatus(status);
		em.persist(hostEntity);
	}

}
