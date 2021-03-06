package br.inatel.ippoller.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/poller")
public interface IPPollerService {

	@GET
	@Path("/start/{ipNetworkAddress}/{cidrMask}")
	@Produces(MediaType.TEXT_HTML)
	void startPoller(@PathParam("ipNetworkAddress") String ipNetworkAddress, @PathParam("cidrMask") String cidrMask);

	@GET
	@Path("/status/{ipAddress}")
	@Produces(MediaType.TEXT_PLAIN)
	boolean getStatus(@PathParam("ipAddress") String ipAddress);
}
