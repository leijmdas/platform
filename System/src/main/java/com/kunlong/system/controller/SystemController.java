package com.kunlong.system.controller;

import com.kunlong.system.model.DictDatatype;
import com.kunlong.system.service.DictDataTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//demo
@RestController
@RequestMapping("system")
public class SystemController {
    @Autowired
    DictDataTypeService dictDataTypeService;

    @RequestMapping("/selectDatatype")
    public List<DictDatatype> test()
    {
        List<DictDatatype> result = dictDataTypeService.selectDatatype();
        return  result;
    }
}
