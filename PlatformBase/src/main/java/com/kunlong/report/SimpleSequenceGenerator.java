package com.kunlong.report;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.CRC32;

/**
 * 
 * @name SimpleSequenceGenerator
 * @author zz  | www.xwparking.com
 * @date 2018年10月19日  
 * @description:
 */
public class SimpleSequenceGenerator {
	
	private SimpleSequenceGenerator() {}

    public static String generate(String prefix) {
        SimpleDateFormat df = new SimpleDateFormat("MMdd");
        return prefix + df.format(new Date()) + getRandomSeq();
    }

    public static String getRandomSeq() {
        long val = getCRC32Value(UUID.randomUUID().toString().replaceAll("-", ""));
        String tmp = Long.toString(val, 31).toUpperCase();
        if(tmp.length()<7) {
        	tmp = StringUtils.leftPad(tmp, 7, "0");
        }
        return tmp.replaceAll("I", "X").replaceAll("O", "Y");
    }

    public static long getCRC32Value(String str) {
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());

        return crc32.getValue();
    }
    public static String generateIssueCode(String prefix) {
        SimpleDateFormat df = new SimpleDateFormat("MMdd");
        return prefix +  getRandomSeq().substring(0,3);
    }

}
