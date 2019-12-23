package com.kunlong.platform.service.impl;

import com.kunlong.platform.dao.ErrorCodeMapper;
import com.kunlong.platform.model.Dict_ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorCodeService {
    @Autowired
    ErrorCodeMapper errrorCodeMapper;


    public List<Dict_ErrorCode> getDictErrorCode() {
        return errrorCodeMapper.getErrorMsg();

    }


}
