package com.kunlong.platform.model;

 
import com.kunlong.platform.utils.KunlongUtils;

import java.math.BigDecimal;

public class KunlongModel {

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
        return KunlongUtils.toJSONStringPretty(this);

    }

    public void log(String title) {
    }

    public void log() {
    }
}
