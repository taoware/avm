package com.irengine.tdd.data;

public class VerifyRequestCommand extends RequestCommand {

    private String item;

    public VerifyRequestCommand() {
    	this.type = COMMAND_TYPE_VERIFY;
    }
    
    public VerifyRequestCommand(String machine, String coupon, String item) {
        this();
        this.machine = machine;
        this.coupon = coupon;
        this.item = item;
    }

    public VerifyRequestCommand(String data) {
    	this();
        this.machine = data.substring(2, 10);
        this.coupon = data.substring(10, 20);
        this.item = data.substring(20);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return this.type + this.machine + this.coupon + this.item;
    }
    
}
