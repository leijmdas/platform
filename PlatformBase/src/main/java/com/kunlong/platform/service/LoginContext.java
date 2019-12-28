package com.kunlong.platform.service;


import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.kunlong.platform.context.AppKlongContext;
import com.kunlong.platform.model.LoginSso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Package:
 * Author: ljm
 * Date: Created in 2017年9月12日
 */
@Service
public final class LoginContext {

    @Autowired
    RedisService redisService;

    public Boolean checkTokenExists(String token) {
        return  redisService.checkTokenExists(token);

    }

    public LoginSso getLoginSso(String token) {
        return  redisService.getLoginSso(token);

    }

    public void login(String token,LoginSso value) {
        if (token == null) {
            return;
        }
        redisService.setLoginSso(token,value );

    }


    //登出，根据token删除会话信息
    public void logout(String token) {
        if (token == null) {
            return;
        }
         
    }


    public static void genPicCode(String ip, HttpServletResponse response) throws IOException {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-ytb.check=0, pre-ytb.check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        DefaultKaptcha captchaProducer = AppKlongContext.getAppCtxt().getBean("captchaProducer", DefaultKaptcha.class);
        String capText =  captchaProducer.createText().substring(0,4);
        //EhcacheContext.getEhcacheContext().getCachePicCode().put(ip,capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    public static byte[] genPicCode2Byte(String ip, HttpServletResponse response) throws IOException {

        DefaultKaptcha captchaProducer = AppKlongContext.getAppCtxt().getBean("captchaProducer", DefaultKaptcha.class);
        String capText =  captchaProducer.createText().substring(0,4);
        //EhcacheContext.getEhcacheContext().getCachePicCode().put(ip, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ByteArrayOutputStream outs = new ByteArrayOutputStream(1024);
        ImageIO.write(bi, "jpg", outs);
        try {
            outs.flush();
        } finally {
            outs.close();
        }
        return outs.toByteArray();
    }

    public static void checkPicCode(String ip, String picCode) throws IOException {
//        Cache.ValueWrapper cv=EhcacheContext.getEhcacheContext().getCachePicCode().get(ip);
//        if (cv==null || !picCode.equalsIgnoreCase(cv.get().toString())) {
//            throw new YtbError(YtbError.CODE_PICCODE_INVALID);
//        }
    }


}
