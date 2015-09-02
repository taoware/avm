package com.irengine.tdd.network.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

@Component
@Qualifier("tcpChannelInitializer")
public class TCPChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Autowired
	private
	LoggingHandler loggingHandler;

	@Autowired
	StringDecoder stringDecoder;

	@Autowired
	StringEncoder stringEncoder;

	@Autowired
	TCPServerHandler serverHandler;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("logging", loggingHandler);
		pipeline.addLast("frame", avmFrameDecoder());
		pipeline.addLast("decoder", stringDecoder);
		pipeline.addLast("encoder", stringEncoder);
		pipeline.addLast("handler", serverHandler);
	}
	
	public AvmFrameDecoder avmFrameDecoder() {
		return new AvmFrameDecoder();
	}

	public LoggingHandler getLoggingHandler() {
		return loggingHandler;
	}

	public void setLoggingHandler(LoggingHandler loggingHandler) {
		this.loggingHandler = loggingHandler;
	}

	public StringDecoder getStringDecoder() {
		return stringDecoder;
	}

	public void setStringDecoder(StringDecoder stringDecoder) {
		this.stringDecoder = stringDecoder;
	}

	public StringEncoder getStringEncoder() {
		return stringEncoder;
	}

	public void setStringEncoder(StringEncoder stringEncoder) {
		this.stringEncoder = stringEncoder;
	}

	public TCPServerHandler getServerHandler() {
		return serverHandler;
	}

	public void setServerHandler(TCPServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}

}

