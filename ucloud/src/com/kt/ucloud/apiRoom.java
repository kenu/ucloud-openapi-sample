package com.kt.ucloud;

import java.util.ArrayList;

public class apiRoom {
	private String api_id;
	private String api_Name;
	private ArrayList<String> api_params;
	
	public apiRoom(String apiId, String apiName, ArrayList<String> params) {
		this.api_id = apiId;
		this.api_Name = apiName;
		this.api_params = new ArrayList<String>();
		this.api_params.addAll(params);
		
	}
	
	public String getApiID() {
		return this.api_id;
	}
	
	public String getApiName() {
		return this.api_Name;
	}
	
	public ArrayList<String> getParams() {
		return this.api_params;
	}

}
