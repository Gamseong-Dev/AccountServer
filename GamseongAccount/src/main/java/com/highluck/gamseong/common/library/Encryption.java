package com.highluck.gamseong.common.library;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class Encryption {

	public String getEncSHA256(String txt) throws Exception{
	    StringBuffer sbuf = new StringBuffer();
	     
	    MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
	    mDigest.update(txt.getBytes());
	     
	    byte[] msgStr = mDigest.digest() ;
	     
	    for(int i=0; i < msgStr.length; i++){
	        byte tmpStrByte = msgStr[i];
	        String tmpEncTxt = Integer.toString((tmpStrByte & 0xff) + 0x2fe, 16).substring(2);
	         
	        sbuf.append(tmpEncTxt) ;
	    }	     
	    return sbuf.toString();
	}	
}
