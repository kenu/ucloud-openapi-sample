package com.kt.ucloud;


import java.util.ArrayList;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;


public class apiParser {
	
	private ArrayList<apiRoom> apiRooms = new ArrayList<apiRoom>(); 
	public apiParser(String sdkfile) {
		System.out.println(sdkfile);
		File file = new File(sdkfile);
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(file);
			NodeList apiList = doc.getElementsByTagName("n1:api");
			NodeList inputList = doc.getElementsByTagName("n1:input");
			NodeList typeList = doc.getElementsByTagName("n1:type");
						
			
			for(int i=0; i < apiList.getLength(); i++) {
				Node api_node = apiList.item(i);
				String api_id = api_node.getAttributes().getNamedItem("id").getNodeValue();
				String api_name = api_node.getAttributes().getNamedItem("name").getNodeValue();
				String input_type_id = inputList.item(i).getAttributes().getNamedItem("type_ref").getNodeValue();
				
				// System.out.println(api_id + " : " + api_name + " : " + input_type_id);
				ArrayList<String> api_params = new ArrayList<String>();
				
				for(int j=0; j < typeList.getLength(); j++) {
										
					if ( input_type_id.equalsIgnoreCase( typeList.item(j).getAttributes().getNamedItem("id").getNodeValue() ) ) {
						Element e1 = (Element) typeList.item(j);
						
						NodeList param_l = e1.getElementsByTagName("n1:param");
						
						for(int k=0; k < param_l.getLength(); k++) {
													
							String param_name = param_l.item(k).getAttributes().getNamedItem("name").getNodeValue();
							//String param_desc = param_l.item(k).getAttributes().getNamedItem("desc").getNodeValue();
							api_params.add(param_name);
							//System.out.println(param_name +":"+param_desc);
						}
						
						break;
					}
				}
				apiRoom s_room = new apiRoom(api_id, api_name,api_params);
				apiRooms.add(s_room);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// api spec 정보 전달 
	public ArrayList<apiRoom> getApiRooms() {
		return apiRooms;
	}
}
