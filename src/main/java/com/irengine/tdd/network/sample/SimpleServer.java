package com.irengine.tdd.network.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SimpleServer {

    private final Logger log = LoggerFactory.getLogger(SimpleServer.class);
    private String name;
    private int portNumber;

    public SimpleServer(String name, int portNumber) {
        this.name = name;
        this.portNumber = portNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    private class InternalServer implements Runnable {
        public void run() {

            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new SimpleServerInitializer());
                // fix: remove channel option
                //      .childOption(ChannelOption.AUTO_READ, false)
                ChannelFuture f = b.bind(getPortNumber()).sync();
                f.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
            
        }
    }

    public void start() {
        log.info(this.getName() + " start");
        new Thread(new InternalServer()).start();
        log.info(this.getName() + " running");
    }

}
