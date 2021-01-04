package com.kunlong.dubbo.rpt;

import java.util.Map;

public interface EasyPOIUtilApiService {
    String makeExcelSheet(String templateName, String fileName, String sheetName,  Map<String, Object> map);

}
