package com.kunlong.platform.consts;



public enum SessionKeyEnum {

	// ======= app =======
	APP_PARK_INFO("park"),
	APP_THIRD_APP("thirdApp"),
	APP_USERID("userId"), 
	APP_USERGRADEID("userGradeId"),
	APP_TESTFLAG("testFlag"),

	// ======= wap =======
	WAP_PARK_INFO("park"),
	WAP_THIRD_APP("thirdApp"),
	WAP_USERID("userId"), 
	WAP_USERGRADEID("userGradeId"),
	WAP_TESTFLAG("testFlag"),
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
