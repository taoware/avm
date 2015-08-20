package com.irengine.tdd.data;

public class VerifyResponseCommand extends ResponseCommand {

    private String item;
    
    public VerifyResponseCommand(String coupon, boolean status, String item) {
    	super(coupon, status);
    	this.type = COMMAND_TYPE_VERIFY;
    	this.item = item;
    	this.couponLength = coupon.length();
    	this.length = 2 + this.type.length() + this.couponLength + 1 + this.item.length(); 
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
    	return String.format("%03d", this.length) +
    			String.format("%02d", this.couponLength) +
    			this.type +
    			this.coupon +
    			(status ? "1" : "2") +
    			this.item;
    }
    
}
