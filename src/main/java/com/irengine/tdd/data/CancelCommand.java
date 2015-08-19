package com.irengine.tdd.data;

public class CancelCommand extends Command {

    public CancelCommand() {
    	this.type = COMMAND_TYPE_CANCEL;
    }
    
    public CancelCommand(String machine, String coupon) {
        this();
        this.machine = machine;
        this.coupon = coupon;
    }
    
    public CancelCommand(String data) {
    	this();
        this.machine = data.substring(2, 10);
        this.coupon = data.substring(10, 20);
    }

    @Override
    public String toString() {
        return this.type + this.machine + this.coupon;
    }
    
}
