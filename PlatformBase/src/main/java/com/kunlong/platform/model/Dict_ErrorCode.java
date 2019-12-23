package com.kunlong.platform.model;

public class Dict_ErrorCode extends KunlongModel {


    private int errorId;

    private String errorCode;

    private String remark;

    private String remarkChina;

    private String scene;

    private String explain;


    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemarkChina() {
        return remarkChina;
    }

    public void setRemarkChina(String remarkChina) {
        this.remarkChina = remarkChina;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
