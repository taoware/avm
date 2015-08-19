package com.irengine.tdd.data;

public class ResponseCommand implements Command {

	protected int length;
	protected int couponLength;
	protected String type;
	protected String coupon;
	protected boolean status;
	
	public ResponseCommand() {
		
	}

	public ResponseCommand(String coupon, boolean status) {
		this.coupon = coupon;
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
