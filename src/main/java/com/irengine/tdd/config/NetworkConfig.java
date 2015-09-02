package com.irengine.tdd.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.irengine.tdd.network.ServerManager;
import com.irengine.tdd.network.netty.NettyConfig;
import com.irengine.tdd.network.netty.NettyTCPServer;
import com.irengine.tdd.network.netty.ServerManagerImpl;
import com.irengine.tdd.network.netty.TCPChannelInitializer;
import com.irengine.tdd.network.netty.TCPServerHandler;

import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@Configuration
@PropertySource("classpath:network.properties")
public class NetworkConfig {
	
	@Value("${boss.thread.count}")
	private int bossCount;

	@Value("${worker.thread.count}")
	private int workerCount;

	@Value("${tcp.port}")
	private int tcpPort;

	
	@Value("${so.keepalive}")
	private boolean keepAlive;

	@Value("${so.backlog}")
	private int backlog;
	
	@Autowired
	@Qualifier("tcpChannelInitializer")
	private TCPChannelInitializer tcpChannelInitializer;
	
	@Bean
	public ServerManager serverManager() {
		return new ServerManagerImpl();
	}
	
	@Bean(name = "tcpChannelOptions")
	public Map<ChannelOption<?>, Object> tcpChannelOptions() {
		Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
		options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
		options.put(ChannelOption.SO_BACKLOG, backlog);
		return options;
	}
	
	@Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup bossGroup() {
		return new NioEventLoopGroup(bossCount);
	}

	@Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup workerGroup() {
		return new NioEventLoopGroup(workerCount);
	}
	
	@Bean
	public NettyConfig tcpConfig() {
		NettyConfig tcpConfig = new NettyConfig();
		tcpConfig.setChannelOptions(tcpChannelOptions());
		tcpConfig.setBossGroup(bossGroup());
		tcpConfig.setWorkerGroup(workerGroup());
		tcpConfig.setPortNumber(tcpPort);
		return tcpConfig;
	}
	
	@Bean(name = "loggingHandler")
	public LoggingHandler loggingHandler() {
		return new LoggingHandler(LogLevel.INFO);
	}

	@Bean(name = "stringEncoder")
	public StringEncoder stringEncoder() {
		return new StringEncoder();
	}

	@Bean(name = "stringDecoder")
	public StringDecoder stringDecoder() {
		return new StringDecoder();
	}
	
	@Bean(name = "serverHandler")
	public TCPServerHandler serverHandler() {
		return new TCPServerHandler();
	}
	
	@Bean(name = "tcpServer", destroyMethod = "stopServer")
	public NettyTCPServer tcpServer() {
		NettyTCPServer tcpServer = new NettyTCPServer(tcpConfig(), tcpChannelInitializer);
		return tcpServer;
	}
	
}
