package com.irengine.tdd.data;

public class AuditRequestCommand extends RequestCommand {

    public AuditRequestCommand() {
    	this.type = COMMAND_TYPE_AUDIT;
    }
    
    public AuditRequestCommand(String machine, String coupon) {
        this();
        this.machine = machine;
        this.coupon = coupon;
    }
    
    public AuditRequestCommand(String data) {
    	this();
        this.machine = data.substring(2, 10);
        this.coupon = data.substring(10, 20);
    }

    @Override
    public String toString() {
        return this.type + this.machine + this.coupon;
    }
    
}
