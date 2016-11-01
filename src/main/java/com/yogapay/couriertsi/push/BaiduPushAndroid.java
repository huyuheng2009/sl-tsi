package com.yogapay.couriertsi.push;

import java.util.Map;

public class BaiduPushAndroid {
	
	private String title ;
	
	private String description;
	
	/*private String  notification_builder_id; //可选  
	
	private String  notification_basic_style ;//可选  
	
	private String  open_type ;//可选  
	
	private String  url; //可选  
	
	private String pkg_content ; //可选  
*/	
	
	private String  open_type ;//可选  
	
	private Map<String, Object> custom_content ;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Object> getCustom_content() {
		return custom_content;
	}

	public void setCustom_content(Map<String, Object> custom_content) {
		this.custom_content = custom_content;
	}

	public String getOpen_type() {
		return open_type;
	}

	public void setOpen_type(String open_type) {
		this.open_type = open_type;
	}


}
