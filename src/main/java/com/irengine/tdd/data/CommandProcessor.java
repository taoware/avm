package com.irengine.tdd.data;

public class CommandProcessor {
	
	public static final String SAMPLE_COUPON = "2875414474";
	public static final String SAMPLE_ITEM = "21";

	public static ResponseCommand action(RequestCommand request) {
		if (request instanceof VerifyRequestCommand) {
			return verify((VerifyRequestCommand)request);
		}
		else if (request instanceof AuditRequestCommand) {
			return audit((AuditRequestCommand)request);
		}
		else if (request instanceof CancelRequestCommand) {
			return cancel((CancelRequestCommand)request);
		}
		else {
			return null;
		}
	}
	
	public static VerifyResponseCommand verify(VerifyRequestCommand request) {
		
		String coupon = request.getCoupon();
		boolean status = coupon.equals(SAMPLE_COUPON);
		VerifyResponseCommand response = new VerifyResponseCommand(coupon, status, SAMPLE_ITEM);
		
		return response;
	}

	public static AuditResponseCommand audit(AuditRequestCommand request) {
		
		String coupon = request.getCoupon();
		boolean status = coupon.equals(SAMPLE_COUPON);
		AuditResponseCommand response = new AuditResponseCommand(coupon, status);
		
		return response;
	}

	public static CancelResponseCommand cancel(CancelRequestCommand request) {
		
		String coupon = request.getCoupon();
		boolean status = coupon.equals(SAMPLE_COUPON);
		CancelResponseCommand response = new CancelResponseCommand(coupon, status);
		
		return response;
	}

}
