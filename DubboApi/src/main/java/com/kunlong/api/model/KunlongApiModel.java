package com.kunlong.api.model;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;

//@NewScope("prototype")
public class KunlongApiModel {

    protected BigDecimal newBigDecimal(float value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    BigDecimal newBigDecimal(double value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    BigDecimal newBigDecimal(int value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.PrettyFormat, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);

    }


}
