package com.kunlong.platform.context.RestMessage;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kunlong.platform.context.rest.IRestProcess;
import com.kunlong.platform.dao.IUserContext;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.service.DefaultUserContext;
import com.kunlong.platform.context.AppKlongContext;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Service
public class MsgHandler {
    public final static String UTF_8 = "UTF-8";
    public MsgRequest req;
    public MsgResponse resp;
    protected IUserContext userContext = new DefaultUserContext();


    private IRestProcess restProcess;

    public MsgHandler() {

    }

    public MsgHandler(IUserContext userContext) {
        this.userContext = userContext;
    }


    public IUserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(IUserContext userContext) {
        this.userContext = userContext;
    }

    public MsgRequest getReq() {
        return req;
    }

    public void setReq(MsgRequest req) {
        this.req = req;
    }

    public MsgResponse getResp() {
        return resp;
    }

    public void setResp(MsgResponse resp) {
        this.resp = resp;
    }
    public void parseRequest(MsgRequest preq) {
        req = preq;//req.parse(reqBody);
        resp = new MsgResponse(req);
        resp = success_default();
        if (userContext == null) {
            userContext = new DefaultUserContext();
        }
        if (req.token != null) {
            userContext.setLoginSso(AppKlongContext.getLoginContext().getLoginSso(req.token));

        }

        userContext.checkUserRightValid(req);
    }


    public void parseRequest(String reqBody) {
        req = req.parse(reqBody);
        resp = new MsgResponse(req);
        resp = success_default();
        if (userContext == null) {
            userContext = new DefaultUserContext();
        }
        if (req.token != null) {
            userContext.setLoginSso(AppKlongContext.getLoginContext().getLoginSso(req.token));

        }

        userContext.checkUserRightValid(req);
    }


    public MsgResponse success_default() {
        return success( "{}");
    }

    public MsgResponse success(String retBody) {
        resp = new MsgResponse(req);
        resp.success(retBody);
        return resp;
    }
    public MsgResponse failure(int retcode, String retmsg) {
        resp = new MsgResponse(req);
        resp.failure(retcode, retmsg);
        return resp;
    }
    public MsgResponse buildMsg(int retcode, String retmsg, String retBody) {
        resp = new MsgResponse(req);
        resp.buildMsg(retcode, retmsg, retBody);
        return resp;
    }

    public MsgResponse buildMsg(Exception e) {
        e.printStackTrace();
        KunlongError ye = new KunlongError(KunlongError.CODE_UNKNOWN_ERROR);
        return buildMsg(ye.getRetcode(), e.toString(), "{}");

    }

    public MsgResponse buildMsg(KunlongError ye) {
        ye.printStackTrace();
        return buildMsg(ye.getRetcode(), ye.getMsg(), "{}");
    }


    public  String toString() {
        return toJSONStringPretty(this.resp);
    }


    public String parseRequest(IRestProcess restProcess, MsgRequest preq, HttpServletRequest request, HttpServletResponse response) {

        this.restProcess = restProcess;
        response.setCharacterEncoding("utf-8");

        try {
            parseRequest(preq);
            restProcess.process(this, request, response);

        } catch (KunlongError e) {
                 e.printStackTrace();

            failure(e.getRetcode(), e.toString());
        } catch (Exception e) {
                 e.printStackTrace();

            //KunlongError ye = new KunlongError(KunlongError.CODE_UNKNOWN_ERROR);
            failure(-1, e.toString());
        }finally {
            //YtbLog.logDebug("Exit " + req.buildCmdInfo(), this.resp);
        }

        return resp.toJSONString();
    }
    public String parseRequest(IRestProcess restProcess, String reqBody, HttpServletRequest request, HttpServletResponse response) {

        this.restProcess = restProcess;
        response.setCharacterEncoding("utf-8");

        try {
            parseRequest(reqBody);
            restProcess.process(this, request, response);

        } catch (KunlongError e) {
            e.printStackTrace();

            failure(e.getRetcode(), e.toString());
        } catch (Exception e) {
            e.printStackTrace();

            KunlongError ye = new KunlongError(KunlongError.CODE_UNKNOWN_ERROR);
            failure(ye.getRetcode(), e.toString());
        }finally {
            //YtbLog.logDebug("Exit " + req.buildCmdInfo(), this.resp);
        }

        return resp.toJSONString();
    }
    public MsgResponse parse (IRestProcess restProcess, String reqBody, HttpServletRequest request, HttpServletResponse response) {

        this.restProcess = restProcess;
        response.setCharacterEncoding("utf-8");

        try {
            parseRequest(reqBody);
            restProcess.process(this, request, response);

        } catch (KunlongError e) {
                 e.printStackTrace();

            failure(e.getRetcode(), e.toString());
        } catch (Exception e) {
                 e.printStackTrace();
            KunlongError ye = new KunlongError(KunlongError.CODE_UNKNOWN_ERROR);
            failure(ye.getRetcode(), e.toString());
        }finally {
            //YtbLog.logDebug("Exit " + req.buildCmdInfo(), this.resp);
        }

        return resp ;
    }

    public static String toJSONStringPretty(Object o) {
        return JSONObject.toJSONString(o, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat);
    }

    public static String toJSONStringPrettyWriteMapNullValue(Object o) {
        return JSONObject.toJSONString(o, SerializerFeature.SkipTransientField, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
    }

    public static String base64toStr(String base64Str) throws UnsupportedEncodingException {
        byte[] r = Base64.getDecoder().decode(base64Str.getBytes("UTF-8"));
        return new String(r, "UTF-8");
    }

    public static String base64toStr(byte[] base64) throws UnsupportedEncodingException {
        byte[] r = Base64.getDecoder().decode(base64);
        return new String(r, "UTF-8");
    }


    public static String byte2Base64(byte[] b) throws UnsupportedEncodingException {
        byte[] r = Base64.getEncoder().encode(b);
        return new String(r, "UTF-8");
    }



    public static HttpEntity buildEntity(String url, String msgBody, File postFile) {
        MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
        multipartEntity.setContentType(ContentType.MULTIPART_FORM_DATA);
        multipartEntity.addTextBody("msgBody", msgBody);
        FileBody fundFileBin = new FileBody(postFile);
        multipartEntity.addPart("file", fundFileBin);
        return multipartEntity.build();
    }

}
