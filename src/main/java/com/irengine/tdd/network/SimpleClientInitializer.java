package com.irengine.tdd.network;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class SimpleClientInitializer extends ChannelInitializer<SocketChannel> {
	
    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();

    private static final SimpleClientHandler CLIENT_HANDLER = new SimpleClientHandler();


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()),
                DECODER,
                ENCODER,
                CLIENT_HANDLER);
   }

}
