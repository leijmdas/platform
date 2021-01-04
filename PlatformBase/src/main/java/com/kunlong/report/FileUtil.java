package com.kunlong.report;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static  File makeTmpFile(String pre,String fileName) throws IOException {
        String fileNameNew = PathConsts.EXPORT_XLS_TMP_PATH + SimpleSequenceGenerator.generate(pre) + fileName;
        File p = new File(PathConsts.EXPORT_XLS_TMP_PATH);
        if(!p.exists()){
            p.mkdirs();
        }
        File f = new File(fileNameNew);

        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }


}
