package com.kunlong.platform.support.service;

public interface AuthService {


	AuthToken createToken(String businessKey);
	AuthToken createToken(String businessKey, long timeoutSeconds);
	
	AuthToken getToken(String token);
	public static class AuthToken {
		private String token;
		private long expiresIn;
		private long createTime;
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public long getExpiresIn() {
			return expiresIn;
		}
		public void setExpiresIn(long expiresIn) {
			this.expiresIn = expiresIn;
		}
		public long getCreateTime() {
			return createTime;
		}
		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}
		
	}



}
