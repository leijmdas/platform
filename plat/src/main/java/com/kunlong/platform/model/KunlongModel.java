package com.kunlong.platform.model;

 
import com.kunlong.platform.utils.KunlongUtils;

import java.math.BigDecimal;

//@NewScope("prototype")
public class KunlongModel {

    public BigDecimal newBigDecimal(float value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal newBigDecimal(BigDecimal value) {
        return  value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    public BigDecimal newBigDecimal(double value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal newBigDecimal(int value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String toString() {
        return KunlongUtils.toJSONStringPretty(this);

    }

    public void log(String title) {
    }

    public void log() {
    }


    public BigDecimal newBigDecimal(int newScale,float value ) {
        return new BigDecimal(value).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal newBigDecimal(int newScale,BigDecimal value) {
        return  value.setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }
    public BigDecimal newBigDecimal(int newScale,double value) {
        return new BigDecimal(value).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal newBigDecimal(int newScale,int value) {
        return new BigDecimal(value).setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }

}
