package com.kunlong.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.kunlong.dubbo.rpt.EasyPOIUtilApiService;
import com.kunlong.report.EasyPOI41Util;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;


@Service(version = "${dubbo.service.version}", interfaceClass = EasyPOIUtilApiService.class)
public class EasyPOIUtilProvider  implements EasyPOIUtilApiService {
    @Override
    public String makeExcelSheet(String templateName, String fileName, String sheetName,  Map<String, Object> map ) {
        // Map map=JSON.parseObject(maps,Map.class);
        try {
            return EasyPOI41Util.makeExcelSheet(templateName, fileName, sheetName, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
