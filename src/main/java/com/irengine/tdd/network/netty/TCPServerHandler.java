package com.irengine.tdd.network.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irengine.tdd.data.CommandFactory;
import com.irengine.tdd.data.CommandProcessor;
import com.irengine.tdd.data.RequestCommand;
import com.irengine.tdd.data.ResponseCommand;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class TCPServerHandler extends SimpleChannelInboundHandler<String> {

    private final Logger log = LoggerFactory.getLogger(TCPServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {    	
    	log.debug("Client connected.");
		super.channelActive(ctx);
    }
    
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	log.debug("Client disconnected.");
		super.channelInactive(ctx);
	}

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String request) {

    	RequestCommand requestCommand = CommandFactory.CreateCommand(request);
        log.info("Request: " + request + ", " + requestCommand.getClass().toString() + ": " + requestCommand.toString());
        ResponseCommand responseCommand = CommandProcessor.action(requestCommand);
        if (null != responseCommand) {
            String response = responseCommand.toString();
            log.info("Response: " + response + ", " + responseCommand.getClass().toString() + ": " + responseCommand.toString());
            ctx.write(response);
            ctx.flush();
        }

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