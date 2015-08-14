package com.irengine.tdd.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleClient {

    private final Logger log = LoggerFactory.getLogger(SimpleClient.class);
    private String name;
    private String hostName = "127.0.0.1";
    private int portNumber;

    public SimpleClient(String name, int portNumber) {
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

    private class InternalClient implements Runnable {
        public void run() {

            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new SimpleClientInitializer());

                // 连接服务端
                Channel ch = b.connect(hostName, portNumber).sync().channel();

                String importantInfo[] = {
                        "Mares eat oats",
                        "Does eat oats",
                        "Little lambs eat ivy",
                        "A kid will eat ivy too"
                };
                for (int i = 0;
                     i < importantInfo.length;
                     i++) {

                    Thread.sleep(1000);

                    log.info("Client: " + importantInfo[i]);
                    ch.writeAndFlush(importantInfo[i] + "\r\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // The connection is closed automatically on shutdown.
                group.shutdownGracefully();
            }
        }
    }

    public void start() {
        log.info(this.getName() + " start");
        new Thread(new InternalClient()).start();
        log.info(this.getName() + " running");
    }

}
