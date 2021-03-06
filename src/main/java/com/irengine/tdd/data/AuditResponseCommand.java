package com.irengine.tdd.data;

public class AuditResponseCommand extends ResponseCommand {
    
    public AuditResponseCommand(String coupon, boolean status) {
    	super(coupon, status);
    	this.type = COMMAND_TYPE_AUDIT;
    	this.couponLength = coupon.length();
    	this.length = 2 + this.type.length() + this.couponLength + 1; 
    }

    @Override
    public String toString() {
    	return String.format("%03d", this.length) +
    			String.format("%02d", this.couponLength) +
    			this.type +
    			this.coupon +
    			(status ? "1" : "2");
    }
    
}
