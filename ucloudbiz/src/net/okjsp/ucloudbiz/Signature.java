package net.okjsp.ucloudbiz;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.cloud.utils.encoding.Base64;

public class Signature {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		String secretkey = "keokzhd";
//		String secretkey = "N1TrIJLqP-yaYEJOKnP9Sv-vx7dulme43Zi71mQ65APTDIr_UUnq7lnCGY76J05K0G9W1e4PmMlh3B_neelkFQ";
//		String commandString = "command=listVirtualMachines&name=VM_33111&state=Running&response=xml&apiKey=miVr6X";
		String commandStringOrderred = "apiKey=miVr6X&command=listVirtualMachines&name=VM_33111&response=xml&state=Running".toLowerCase();
		String encode = getSignature(secretkey, commandStringOrderred);
		System.out.println(encode);
		System.out.println("ZC1u%2B03yj592LZ%2BgmA3ac%2FQThws%3D");
	}

	public static String getSignature(String secretkey,
			String commandStringOrdered) throws NoSuchAlgorithmException,
			InvalidKeyException, UnsupportedEncodingException {
		Mac mac = Mac.getInstance ( "HmacSHA1" );
		SecretKeySpec keySpec = new SecretKeySpec(secretkey.getBytes(), "HmacSHA1"); 
		mac.init( keySpec ); 
		mac.update ( commandStringOrdered.getBytes() );
		byte[] encryptedBytes = mac.doFinal();
		String encode = URLEncoder.encode(Base64.encodeBytes(encryptedBytes), "UTF-8");
		return encode;
	}
}
