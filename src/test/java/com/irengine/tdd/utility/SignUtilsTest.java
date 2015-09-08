package com.irengine.tdd.utility;

import static org.junit.Assert.assertEquals;

import java.security.MessageDigest;

import org.junit.Test;

public class SignUtilsTest {
	
	@Test
	public void testGenerateKey() throws Exception {
		String val1 = "yhq2015010001"
				+ "13601234567";
				
		assertEquals("653209bc6235b48c72f08921d514dbb5", getMD5(val1));
		
		String val2 = "252002148160025" + 
				"13601234567" +
				"yhq20150100002" +
				"500";
		
		assertEquals("TrphUqE4VP0IA1SJFKLm0g2UM168ujQUujuVEl1QNMHNBSV3uCj637dfMTVwthXQra%2FORx5%2FRrbf%0AT6x0U%2F2%2FueYb6dU5C%2B8Hp%2FuQqQf4DdUlARZSRXsNUsk8B4bVrkdiNsYJE%2FeKxMDdN4M5SA5AdZQj%0Ab6LSlntmyCIR1ch1298%3D",
				SignUtils.genSignByRSA(val2));
	}
	
    private static String getMD5(String val) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        
        md.update(val.getBytes());

        byte[] mdbytes = md.digest();
     
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }

}
