package com.irengine.tdd.data;

public class CancelRequestCommand extends RequestCommand {

    public CancelRequestCommand() {
    	this.type = COMMAND_TYPE_CANCEL;
    }
    
    public CancelRequestCommand(String machine, String coupon) {
        this();
        this.machine = machine;
        this.coupon = coupon;
    }
    
    public CancelRequestCommand(String data) {
    	this();
        this.machine = data.substring(2, 10);
        this.coupon = data.substring(10, 20);
    }

    @Override
    public String toString() {
        return this.type + this.machine + this.coupon;
    }
    
}
