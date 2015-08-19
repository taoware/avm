package com.irengine.tdd.data;

public class VerifyCommand extends Command {

    private String item;

    public VerifyCommand() {
    	this.type = COMMAND_TYPE_VERIFY;
    }
    
    public VerifyCommand(String machine, String coupon, String item) {
        this();
        this.machine = machine;
        this.coupon = coupon;
        this.item = item;
    }

    public VerifyCommand(String data) {
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
