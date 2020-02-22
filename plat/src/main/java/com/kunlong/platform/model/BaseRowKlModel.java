package com.kunlong.platform.model;

import com.alibaba.excel.metadata.BaseRowModel;
import com.kunlong.platform.utils.KunlongUtils;

import java.math.BigDecimal;

public class BaseRowKlModel extends BaseRowModel{

    @Override
    public String toString() {
        return KunlongUtils.toJSONStringPretty(this);

    }

    public static BigDecimal newBigDecimal(float value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal newBigDecimal(BigDecimal value) {
        return  value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    public static BigDecimal newBigDecimal(double value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal newBigDecimal(int value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }



    public static BigDecimal newBigDecimal(int newScale,float value ) {
        return new BigDecimal(value).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal newBigDecimal(int newScale,BigDecimal value) {
        return  value.setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }
    public static BigDecimal newBigDecimal(int newScale,double value) {
        return new BigDecimal(value).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal newBigDecimal(int newScale,int value) {
        return new BigDecimal(value).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }
}
