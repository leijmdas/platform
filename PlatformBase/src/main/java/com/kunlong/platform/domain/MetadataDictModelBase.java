package com.kunlong.platform.domain;

import com.kunlong.platform.model.KunlongModel;

import java.io.Serializable;
import java.util.List;

;

/**
 * DictDataModel 数据字典
 * @author generator
 * @date 2020年02月20日
 */

public class MetadataDictModelBase extends KunlongModel implements Serializable {

	public List<MetadataFieldModel> getMetadataFieldModels() {
		return metadataFieldModels;
	}

	public void setMetadataFieldModels(List<MetadataFieldModel> metadataFieldModels) {
		this.metadataFieldModels = metadataFieldModels;
	}

	List<MetadataFieldModel> metadataFieldModels;

	private static final long serialVersionUID = 1L;

    // ==== 自定义属性 ====
}