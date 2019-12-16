package com.kunlong.platform.model;

//import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class LoginSso extends KunlongModel implements Serializable {

    public Api_KeyModel getApiKeyModel() {
        return apiKeyModel;
    }

    public void setApiKeyModel(Api_KeyModel apiKeyModel) {
        this.apiKeyModel = apiKeyModel;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    Api_KeyModel apiKeyModel = new Api_KeyModel();
    //LoginSsoJson
    //private LoginSsoJson loginSsoJson;

    //@ExcelProperty(value = "ssoid", index = 0)
    private Integer ssoid;

    /**
     * 用户主键
     */
    //@ExcelProperty(value = "userId", index = 1)
    private Long userId;

    /**
     * SessionID
     */
    private String token;

    /**
     * 登录时间
     */

    //@ExcelProperty(value = "登录时间", index = 2)
    private Date loginTime;

    /**
     * 用户登录的IP
     */
    private String loginIp;

    /**
     * 短信验证码code
     */
    private String code;

    /**
     * 登陆信息
     */
    transient  private String json;

    private static final long serialVersionUID = 1L;

    public Integer getSsoid() {
        return ssoid;
    }

    public void setSsoid(Integer ssoid) {
        this.ssoid = ssoid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserid(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public  String getJson() {
        return json;
    }

    public void setJson( String json) {
        this.json = json;
    }



    public boolean isUserManager(){

        return  true;
    }

    public boolean isTest(){

        return false;
    }


}