package com.irengine.tdd.data;

public class Command {
	
	public static final String COMMAND_TYPE_INVALID = "00";
	public static final String COMMAND_TYPE_VERIFY = "10";
	public static final String COMMAND_TYPE_AUDIT = "11";
	public static final String COMMAND_TYPE_CANCEL = "12";
	
    protected String type;
    protected String machine;
    protected String coupon;
    
    public Command() {
    	
    }
    
    public Command(String type, String machine, String coupon) {
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