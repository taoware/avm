package com.irengine.tdd.data;

public class RequestCommand implements Command {
	
	protected String type;
    protected String machine;
    protected String coupon;
    
    public RequestCommand() {
    	
    }
    
    public RequestCommand(String type, String machine, String coupon) {
    	this.type = type;
    	this.machine = machine;
    	this.coupon = coupon;
    }
	
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
    
    @Override
    public String toString() {
        return this.type + this.machine + this.coupon;
    }

}