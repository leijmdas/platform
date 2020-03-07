package com.kunlong.platform.service.autowebpage;

import com.kunlong.core.util.StringUtil;
import com.kunlong.platform.consts.DictConsts;
import com.kunlong.platform.consts.DictSrcConsts;
import com.kunlong.platform.consts.WebPageConsts;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.service.MetadataJoinService;
import com.kunlong.platform.utils.KunlongUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebPageUtil {
    @Autowired
    MetadataDictModelService metadataDictModelService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    MetadataJoinService metadataJoinService;


    public List<String> makeWebPage(MetadataJoinService  metadataJoinService,Integer metadataId) throws IOException {
        this.metadataJoinService=metadataJoinService;
        String filename=makeWebPageTable(metadataId);
        List<String> list = new ArrayList<>();
        list.add(filename);
        filename = makeWebPageEdit(metadataId);
        list.add(filename);

        filename = makeWebPageDefaultValue(metadataId);
        list.add(filename);
        return list;
    }

    //Table主页
    public String  makeWebPageTable(Integer metadataId) throws IOException {

        StringBuilder webPageTable = new StringBuilder(1024);

        Map<String, Map<String, String>> mapDict = new LinkedHashMap<>();
        webPageTable.append("\n<v-table ref='table' :dblclick='edit' :page='page' ");
        webPageTable.append(":table-minheight='450' @dataloaded='onDataloaded'>");
        List<MetadataFieldModel> models = metadataJoinService.findAllByMetadataId(metadataId, -1);
        for (MetadataFieldModel model : models) {
            model.setFieldMemo(model.getFieldMemo().trim());
            model.setFieldName(StringUtil.underlineToCamel(model.getFieldName().trim()));
            String fieldName = model.getFieldName();

            Boolean hasSrcDisplay = !model.getRefObject().isEmpty() && DictSrcConsts.DICT_SRC_DISPLAY_FIELD.equals(model.getFieldSrc());
            Boolean hasSrcPool = !model.getRefObject().isEmpty() && DictSrcConsts.DICT_SRC_POOL.equals(model.getFieldSrc());

            if (model.getFieldDisplaysize() < 50) {
                model.setFieldDisplaysize(120);
            }


            if (model.getFieldVisible() || hasSrcDisplay) {
                webPageTable.append("\n\t");
                webPageTable.append(String.format("<el-table-column prop='%s' :sortable='true' label='%s' width='%d'>",
                        model.getFieldName(), model.getFieldMemo().trim(), model.getFieldDisplaysize()));
                webPageTable.append("\n\t\t<template slot-scope='{row}'>");
                if (hasSrcPool) {
                    Map<String, String> dict = parseRefObject(model);
                    mapDict.put(fieldName.toUpperCase(), dict);
                    webPageTable.append(String.format("\n\t\t\t{{DICT.%S[row.%s]}}", fieldName, fieldName));
                }
                else if (model.isBoolean()) {
                    webPageTable.append(String.format("\n\t\t\t<span :style='row.%s?\"color:red\":\"color:black\"'>",model.getFieldName()));
                    webPageTable.append(String.format("\n\t\t\t\t{{row.%s?'是':'否'}}", fieldName));
                    webPageTable.append("\n\t\t\t</span>");

                }
//                else if (model.isDateOnly()) {
//
//                }
//
                else if (hasSrcDisplay) {
                    webPageTable.append(String.format("<span style='color:%s'>", model.getDisplayColor()));
                    webPageTable.append(String.format("\n\t\t\t{{row.%s}}", model.getRefObject().trim()));
                    webPageTable.append("</span>");
                }
                else {
                    if (model.getDisplayColor().trim().isEmpty()) {
                        webPageTable.append(String.format("\n\t\t\t{{row.%s}}", model.getFieldName()));
                    } else {
                        webPageTable.append(String.format("<span style='color:%s'>", model.getDisplayColor()));
                        webPageTable.append(String.format("\n\t\t\t{{row.%s}}", model.getFieldName()));
                        webPageTable.append("</span>");
                    }
                }
                webPageTable.append("\n\t\t</template>");
                webPageTable.append("\n\t</el-table-column>");
            }
        }

        //add 操作 column
        webPageTable.append("\n\t<el-table-column width='80' label='操作' :fixed='\"right\"'>");

        webPageTable.append("\n\t\t<template slot-scope='scope'>");
        webPageTable.append("\n\t\t\t<el-button type='text' title='编辑'@click='edit(scope.row)'>");
        webPageTable.append("\n\t\t\t<i class='el-icon-edit'></i>");
        webPageTable.append("\n\t\t\t</el-button>");
        webPageTable.append("\n\t\t\t<el-button type='text' @click='del(scope.row,scope.$index)' title='删除' >");
        webPageTable.append("\n\t\t\t<span style='color: red'> <i class='el-icon-delete red'></i></span>");
        webPageTable.append("\n\t\t\t</el-button>");
        webPageTable.append("\n\t\t</template>");
        webPageTable.append("\n\t</el-table-column>");
        webPageTable.append("\n</v-table>");

        String filename = write2File(metadataId, webPageTable, WebPageConsts.WEBPAGE_TABLE);

        //write  Dict
        String filenameDict = write2File(metadataId,
                "\nDICT: " + KunlongUtils.toJSONStringPretty(mapDict),
                WebPageConsts.WEBPAGE_TABLE_DICT);

        return filename + "," + filenameDict;

    }

    //新增时默认值
    String  makeWebPageDefaultValue(Integer metadataId) throws IOException {
        StringBuilder defaultVal = new StringBuilder(512);
        List<MetadataFieldModel> models = metadataJoinService.findAllByMetadataId(metadataId, -1);

        defaultVal.append("\n\tconst defaultEntity = {");

        for (MetadataFieldModel model : models) {
            model.setFieldMemo(model.getFieldMemo().trim());
            model.setFieldName(StringUtil.underlineToCamel(model.getFieldName().trim()));

            defaultVal.append("\n\t\t");
            defaultVal.append(model.getFieldName()).append(": ");
            if (model.getFieldPk()) {
                defaultVal.append(" null,");
            } else if (model.getFieldIsNull()) {
                defaultVal.append(" null,");
            } else if (model.isString()) {
                defaultVal.append(" '").append(model.getFieldDefault()).append("' ,");

            } else if (model.isBoolean()) {
                defaultVal.append(" false,");
            } else if (model.isDecimal()) {
                if (model.getFieldDefault().trim().isEmpty()) {
                    defaultVal.append(" 0.0,");
                } else {
                    defaultVal.append(model.getFieldDefault()).append(",");
                }
            } else if (model.isDate()) {
                defaultVal.append(" '");
                if (model.getFieldDefault().trim().isEmpty()) {
                    defaultVal.append("2020-01-01 00:00:00");
                } else {
                    defaultVal.append(model.getFieldDefault());
                }
                defaultVal.append("' ,");

            } else {
                defaultVal.append(" 0,");
            }
            defaultVal.append("\t//  ").append(model.getFieldMemo());

        }
        ;
        defaultVal.append("\n\t};");

        return write2File(metadataId, defaultVal, WebPageConsts.WEBPAGE_EDIT_DEFAULT);


    }

    String write2File(Integer metadataId, StringBuilder s, String fSub) throws IOException {
       return  write2File(metadataId,s.toString(),fSub);
    }

    String write2File(Integer metadataId, String s, String fnSub) throws IOException {
        MetadataDictModel metadataDict = metadataDictModelService.findById(metadataId);

        if (metadataDict != null) {

            String filename = WebPageConsts.WEBPAGE_PATH + metadataDict.getMetadataName() + fnSub;
            logger.info("start write file: {}", filename);
            File file = new File(filename);
            try (FileOutputStream f = new FileOutputStream(file)) {
                f.write(s.getBytes("UTF-8"));
                logger.info("end write file: {}",filename);
            }
            return filename;
        }

        return metadataId + " 不存在！";
    }

    //编辑页面
    String  makeWebPageEdit(Integer metadataId) throws IOException {

        StringBuilder page = new StringBuilder(1024);
        List<MetadataFieldModel> models = this.metadataJoinService.findAllByMetadataId(metadataId, -1);
        page.append("\n<el-form :model='entity' :rules='rules' ref='form'  label-width='100px' class='dialog-form'>\n");

        page.append("\n\t<el-row :span='24'>");
        for (MetadataFieldModel model : models) {
            if (!model.getFieldVisible()) {
                continue;
            }
            if (DictConsts.FIELD_DISPLAY_BIG <= model.getFieldSize()) {
                page.append("\n\t\t<el-col :span='24'>");
            } else if (DictConsts.FIELD_DISPLAY_MEDUIM <= model.getFieldSize()) {
                page.append("\n\t\t<el-col :span='24'>");
            } else {
                page.append("\n\t\t<el-col :span='12'>");
            }
            page.append("\n\t\t<el-form-item ");

            model.setFieldMemo(model.getFieldMemo().trim());
            model.setFieldName(StringUtil.underlineToCamel(model.getFieldName().trim()));

            logger.info("model:{}", model);
            page.append(String.format("style='width:100%%' label='%s' prop='%s'>", model.getFieldMemo(), model.getFieldName()));
            // add BIT select BOOL类型
            if (model.isDateOnly()) {
                page.append("<el-date-picker style='width:100%'");
                page.append(String.format("v-model='entity.%s'", model.getFieldName().trim()));
                page.append(" format='yyyy 年 MM 月 dd 日'");
                page.append(" value-format='yyyy-MM-dd HH:mm:ss'  type='date'");
                page.append(" placeholder='选择日期'>  </el-date-picker>");
            } else if (model.isBoolean()) {
                page.append(String.format("\n\t\t\t<el-select style='width:100%%' v-model='entity.%s' ", model.getFieldName()));
                if (model.getFieldReadonly()) {
                    page.append(" disabled");
                }
                page.append(">\n\t\t\t\t<el-option :key = 'true' :value = 'true' :label = '\"是\"' ></el-option >");
                page.append("\n\t\t\t\t<el-option :key = 'false' :value = 'false' :label = '\"否\"' ></el-option >");
                page.append("\n\t\t\t</el-select>");
            } else //数据字典
                if (DictSrcConsts.DICT_SRC_DICT_DATA.equals(model.getFieldSrc())) {
                    page.append(String.format("\n\t\t\t<dict-data-select style='width:100%%' v-model='entity.%s'>", model.getFieldName()));
                    page.append("</dict-data-select>");

                } else if (DictSrcConsts.DICT_SRC_POOL.equals(model.getFieldSrc())) {
                    page.append(String.format("\n\t\t\t<el-select style='width:100%%' v-model='entity.%s' ", model.getFieldName()));
                    if (model.getFieldReadonly()) {
                        page.append(" disabled");
                    }
                    page.append(">");
                    Map<String, String> ref = parseRefObject(model);
                    for (Map.Entry<String, String> entry : ref.entrySet()) {
                        page.append(String.format("\n\t\t\t\t<el-option :key='%s' :value='%s' :label='\"%s\"' ></el-option >",
                                entry.getKey(),
                                model.isString() ? "\"" + entry.getKey() + "\"" : entry.getKey(),
                                entry.getKey() + "--" + entry.getValue()));
                    }
                    page.append("\n\t\t\t</el-select>");
                } else {
                    page.append("\n\t\t\t<el-input ");
                    if (DictConsts.FIELD_DISPLAY_BIG <= model.getFieldSize()) {
                        page.append(" type='textarea' :rows='2' ");
                    }
                    if (model.getFieldReadonly()) {
                        page.append("disabled");
                    }
                    page.append(String.format(" placeholder='%s' v-model='entity.%s'>", model.getFieldMemo(), model.getFieldName()));
                    page.append("\n\t\t\t</el-input>");
                }

            page.append("\n\t\t</el-form-item>");
            page.append("\n\t\t</el-col>");
        }

        page.append("\n\t</el-row>");
        page.append("\n</el-form> ");
        logger.info("page: {}", page);

        return write2File(metadataId, page, WebPageConsts.WEBPAGE_EDIT);

    }

    public Map<String, String> parseRefObject(MetadataFieldModel model) {
        String ref = model.getRefObject().trim();
        String[] refTypes = ref.split(",");
        Map<String, String> refMap = new LinkedHashMap<>();
        for (String type : refTypes) {
            String[] kv = type.split("\\|");
            refMap.put(kv[0].trim(), kv[1].trim());
        }
        return refMap;
    }


}
