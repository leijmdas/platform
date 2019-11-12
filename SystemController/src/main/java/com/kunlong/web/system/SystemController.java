package com.kunlong.web.system;


import com.kunlong.api.model.DictDatatypeApiModel;
import com.kunlong.api.service.DictDataTypeApiService;


import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("system")
public class SystemController {
    @Reference
    DictDataTypeApiService dictDataTypeApiService;


    @RequestMapping("selectByPrimaryKey")
    private DictDatatypeApiModel selectByPrimaryKey(int id) {

        return dictDataTypeApiService.selectByPrimaryKey(id);

    }



}
