package com.irengine.tdd.network.netty;

import com.irengine.tdd.network.Server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public interface NettyServer extends Server
{

	public ChannelInitializer<? extends Channel> getChannelInitializer();

	public void setChannelInitializer(ChannelInitializer<? extends Channel> initializer);

	public NettyConfig getNettyConfig();
	
}
