package com.irengine.tdd.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BinaryDecoder extends ByteToMessageDecoder {

    private final Logger log = LoggerFactory.getLogger(BinaryDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    	
    	log.debug("buffer length: " + in.readableBytes() + " buffer read index: " + in.readerIndex());

        if (in.readableBytes() < 2) {
            return;
        }

        ByteBuf command;
        int index = in.readerIndex();
        
        // command type 10 -- verify coupon
        if (in.getByte(index) == '1' && in.getByte(index + 1) == '0') {
            if (in.readableBytes() >= 21) {
                log.debug("Parse command type 10.");
                command = in.readSlice(21);
                out.add(command.retain());
            }
        }
        // command type 11 -- audit coupon
        else if (in.getByte(index) == '1' && in.getByte(index + 1) == '1') {
            if (in.readableBytes() >= 20) {
                log.debug("Parse command type 11.");
                command = in.readSlice(20);
                out.add(command.retain());
            }
        }
        // command type 12 -- cancel audit coupon
        else if (in.getByte(index) == '1' && in.getByte(index + 1) == '2') {
            if (in.readableBytes() >= 20) {
                log.debug("Parse command type 12.");
                command = in.readSlice(20);
                out.add(command.retain());
            }
        }
        // other command type
        else {
        	// dump invalid command
            log.debug("Parse invalid command.");
        	in.clear();
        }
    }

}
