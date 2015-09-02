package com.irengine.tdd.network.netty;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irengine.tdd.network.ServerManager;
import com.irengine.tdd.utility.ApplicationContextHelper;

public class ServerManagerImpl implements ServerManager {
	
	private static final Logger log = LoggerFactory.getLogger(ServerManagerImpl.class);
	private Set<AbstractNettyServer> servers;
	
	public ServerManagerImpl()
	{
		servers = new HashSet<AbstractNettyServer>();
	}

	@Override
	public void startServers() throws Exception {
		log.debug("server started.");
		AbstractNettyServer tcpServer = (AbstractNettyServer)ApplicationContextHelper.getApplicationContext().getBean("tcpServer");
		tcpServer.startServer();
		servers.add(tcpServer);
	}

	@Override
	public void stopServers() throws Exception {
		log.debug("server stopped.");
		for(AbstractNettyServer nettyServer: servers){
			try
			{
				nettyServer.stopServer();
			}
			catch (Exception e)
			{
				log.error("Unable to stop server {} due to error {}", nettyServer, e);
				throw e;
			}
		}
	}

}
