package com.kunlong.platform.model;

 
import com.alibaba.excel.metadata.BaseRowModel;
import com.kunlong.platform.utils.KunlongUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

//@NewScope("prototype")
public class KunlongModel {
    public static Object setDefault(Object obj){
        Field[] fields=obj.getClass().getDeclaredFields();
        for (Field field:fields){
            field.setAccessible(true);
            Object val= null;
            try {
                val = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if(val==null)
            {
                try {
                    if(field.getType().getName().equals("java.lang.String")) {
                        field.set(obj, "");
                    }
                    if(field.getType().getName().equals("java.lang.Integer") && !field.getName().equals("id")) {
                        field.set(obj, 0);
                    }
                    if(field.getType().getName().equals("java.math.BigDecimal")) {
                        field.set(obj, new BigDecimal(0.00));
                    }
                    if(field.getType().getName().equals("java.lang.Boolean")) {
                        field.set(obj, false);
                    }
                    if(field.getType().getName().equals("java.util.Date")) {
                        field.set(obj, new Date());
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return  obj;
    }
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
