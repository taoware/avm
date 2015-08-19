package com.irengine.tdd.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irengine.tdd.data.Command;
import com.irengine.tdd.data.CommandFactory;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class SimpleServerHandler extends SimpleChannelInboundHandler<String> {

    private final Logger log = LoggerFactory.getLogger(SimpleServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {    	
    	log.debug("Client connected.");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String request) {

    	log.info("business logic for " + request);
    	Command command = CommandFactory.CreateCommand(request);
        log.info(command.getClass().toString() + ": " + command.toString());

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
