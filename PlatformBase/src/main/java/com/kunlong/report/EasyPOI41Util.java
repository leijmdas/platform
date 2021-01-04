package com.kunlong.report;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class EasyPOI41Util {
    public static String makeExcelSheet(String templateName, String fileName, String sheetName, Map<String, Object> map) throws IOException {
//        String f=EasyPOI41Util.class.getResource("templates/" + templateName).getPath();
//        if(new File(f).exists()){
//            System.out.println(f+"存在！");
//
//        }else{
//            System.out.println(f+"不存在！");
//            return f+"不存在！";
//        }
        TemplateExportParams exportParams = new TemplateExportParams("templates/" + templateName);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, map);
        workbook.setSheetName(0, sheetName);
        File newFile = FileUtil.makeTmpFile(sheetName, fileName);
        FileOutputStream fos = new FileOutputStream(newFile);
        try {
            workbook.write(fos);
        } finally {
            fos.flush();
            fos.close();
        }

        return newFile.getAbsolutePath();
    }
}
