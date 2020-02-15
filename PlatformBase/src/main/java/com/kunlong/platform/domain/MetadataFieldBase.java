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



}
