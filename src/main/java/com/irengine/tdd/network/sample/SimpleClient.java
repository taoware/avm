package com.irengine.tdd.network.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irengine.tdd.data.AuditRequestCommand;
import com.irengine.tdd.data.CancelRequestCommand;
import com.irengine.tdd.data.Command;
import com.irengine.tdd.data.RequestCommand;
import com.irengine.tdd.data.VerifyRequestCommand;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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

                Channel ch = b.connect(hostName, portNumber).sync().channel();

                ChannelFuture lastWriteFuture = null;
                
                String machine = "90000272";
                String coupon = "2875414474";
                String item = "2";
                
                // send verify command
                Command data10 = new VerifyRequestCommand(machine, coupon, item);
                lastWriteFuture = ch.writeAndFlush(data10.toString());

                // send audit command
                Command data11 = new AuditRequestCommand(machine, coupon);
                lastWriteFuture = ch.writeAndFlush(data11.toString());

                // send cancel command
                Command data12 = new CancelRequestCommand(machine, coupon);
                lastWriteFuture = ch.writeAndFlush(data12.toString());
                
                // send invalid command
                Command data00 = new RequestCommand(Command.COMMAND_TYPE_INVALID, machine, coupon);
                lastWriteFuture = ch.writeAndFlush(data00.toString());

                // send verify command again
                lastWriteFuture = ch.writeAndFlush(data10.toString());

                Thread.sleep(1000);
                lastWriteFuture = ch.writeAndFlush("FF");

                if (lastWriteFuture != null) {
                    lastWriteFuture.sync();
                }
                Thread.sleep(5000);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // The connection is closed automatically on shutdown.
                group.shutdownGracefully();
            }
        	log.info("Client exited.");

        }
    }

    public void start() {
        log.info(this.getName() + " start");
        new Thread(new InternalClient()).start();
        log.info(this.getName() + " running");
    }

}
