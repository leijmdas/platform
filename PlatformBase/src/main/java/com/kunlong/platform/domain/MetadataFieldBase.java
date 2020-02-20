package com.kunlong.platform.domain;

import com.kunlong.platform.model.KunlongModel;

import java.io.Serializable;

public class MetadataFieldBase extends KunlongModel implements Serializable {

    private static final long serialVersionUID = 1L;
            //"blobText":false,
//        "boolean":false,
//        "date":false,
//        "decimal":false,
//        "displayColor":" ",
//
//        "fieldAuto":true,
//        "fieldComponent":0,
//        "fieldDecimal":0,
//        "fieldDefault":null,
//        "fieldDisplaysize":100,
//        "fieldFormat":" ",
//        "fieldId":null,
//        "fieldIsNull":false,
//        "fieldIscal":false,
//        "fieldMax":" ",
//        "fieldMemo":"用户日志标识",
//        "fieldMin":" ",
//        "fieldName":"id",
//        "fieldOrder":1,
//        "fieldPk":true,
//        "fieldReadonly":false,
//        "fieldRemark":"int(11)",
//        "fieldSize":11,
//
//
//        "fieldType":"int",
//        "fieldVisible":true,
//        "ids":null,
//        "metadataDictModel":null,
//        "metadataId":404,
//        "money":false,
//        "pCT":false,
//        "refDisplayID":" ",
//        "refField":" ",
//        "refFilter":" ",
//        "refObject":" ",
//        "refParameter":" ",
//        "refPool":" ",
//        "refTable":" ",
//        "string":false,
//        "tAGIMAGE":false
    private String ids ;

    MetadataDictModel metadataDictModel;
    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

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

    public boolean isBlobText() {
        String dt = getFieldType();
        return dt.equals("BLOB") || dt.equals("MEDIUMBLOB") || dt.equals("TEXT");

    }

}
