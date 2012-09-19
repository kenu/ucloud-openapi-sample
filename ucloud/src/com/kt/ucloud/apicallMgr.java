package com.kt.ucloud;

import java.util.HashMap;

import com.kt.openplatform.sdk.KTOpenApiHandler;


public class apicallMgr {
	private String auth_key = "";
	private String auth_secret = "";
	private KTOpenApiHandler handler;
	
	public apicallMgr(String api_key, String secret) {
		auth_key = api_key;
		auth_secret = secret;
		handler = KTOpenApiHandler.createHandler(auth_key, auth_secret);
		System.out.println("set auth key & secret"+auth_key+" : "+auth_secret);
	}
	
	public HashMap<?,?> apiCall(String api_id, HashMap<String, String> params) {
				
		// https
		boolean bSSL = false;
		System.out.println("false");
		// Make Parameters
		//HashMap<String, String> params = new HashMap<String,String>();
		//params.put("params1", "kkk");	
							
		// make xauth params
		
		HashMap<String, String> xauth_params = new HashMap<String,String>();
		if(handler == null) {
			return null;
		} 
		HashMap<?,?> r = handler.call(api_id, params, null, bSSL);
		
		
		return r;
	}
	
	public String getApiToken() {
		
		return handler.makeApiToken();
	}

}
