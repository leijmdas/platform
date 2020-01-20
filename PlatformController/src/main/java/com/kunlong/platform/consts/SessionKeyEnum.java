package com.kunlong.platform.consts;



public enum SessionKeyEnum {

	// ======= app =======
	APP_THIRD_APP("thirdApp"),
	APP_USERID("userId"),

	// ======= wap =======
	WAP_THIRD_APP("thirdApp"),
	WAP_USERID("userId"),
	// ======= web =======
	WEB_USER("user");
	private String key;
	SessionKeyEnum(String key){
		this.key = key;
	}
	public String getKey() {
		return key;
	}
	
}
