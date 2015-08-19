package com.irengine.tdd.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SimpleServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();

	private static final LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.INFO);
    private static final SimpleServerHandler SERVER_HANDLER = new SimpleServerHandler();
    private static final BinaryDecoder COMMAND_HANDLER = new BinaryDecoder();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                LOGGING_HANDLER,
                COMMAND_HANDLER,
                DECODER,
                ENCODER,
                SERVER_HANDLER
        );
    }

}

