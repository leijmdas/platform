package com.kunlong.platform.domain;

import lombok.Data;

@Data
public class CheckDictResult {
    String metadataName;
    String dataType;
    String dictFieldName;
    String dbFieldName;
    String fieldType;
}
