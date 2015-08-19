package com.irengine.tdd.data;

public class AuditCommand extends Command {

    public AuditCommand() {
    	this.type = COMMAND_TYPE_AUDIT;
    }
    
    public AuditCommand(String machine, String coupon) {
        this();
        this.machine = machine;
        this.coupon = coupon;
    }
    
    public AuditCommand(String data) {
    	this();
        this.machine = data.substring(2, 10);
        this.coupon = data.substring(10, 20);
    }

    @Override
    public String toString() {
        return this.type + this.machine + this.coupon;
    }
    
}
