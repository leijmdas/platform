package com.kunlong.platform.domain;

import com.kunlong.platform.model.KunlongModel;

import java.io.Serializable;

public class MetadataFieldBase extends KunlongModel implements Serializable {

    private static final long serialVersionUID = 1L;

    MetadataDictModel metadataDictModel;
    public MetadataDictModel getMetadataDictModel() {
        return metadataDictModel;
    }

    public void setMetadataDictModel(MetadataDictModel metadataDictModel) {
        this.metadataDictModel = metadataDictModel;
    }

    public String getFieldType() {
        return "VARCHAR";
    }

    public boolean isDate() {
        return getFieldType().equals("DATE") || getFieldType().equals("TIMESTAMP") || getFieldType().equalsIgnoreCase("DATETIME");
    }
    public boolean isBoolean() {
        return getFieldType().equals("BIT") || getFieldType().equalsIgnoreCase("BOOL");
    }

    public boolean isString() {
        return getFieldType().equals("CHAR") || getFieldType().equalsIgnoreCase("VARCHAR");
    }

    public boolean isDecimal() {
        return getFieldType().equals("DECIMAL");

    }

    public  boolean isTAGIMAGE() {
        return getFieldType().equals("TAGIMAGE");

    }


    public  boolean isPCT() {
        return getFieldType().equals("PCT");

    }

    public boolean isMoney() {
        return getFieldType().equals("MONEY");

    }

    public    boolean isBlobText() {
        String dt = getFieldType();
        return dt.equals("BLOB")
                || dt.equals("MEDIUMBLOB")
                || dt.equals("TEXT");

    }


}
