package com.kunlong.platform.test;

import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import com.jtest.utility.testlog.JTestLog;
import com.kunlong.PfApp;

public class TestFastDfs extends ITestImpl {

    @JTest
    @JTestClass.title(".test0001_fastdfs")
    @JTestClass.pre("")
    @JTestClass.step("test0001_fastdfs")
    @JTestClass.exp("ok")
    public void test0001_fastdfs() throws Exception {
        String confUrl = PfApp.class.getResource("/fdfs_client.properties").getPath();
        System.out.println(confUrl);
        FastDFSClient fastDFSClient = new FastDFSClient(confUrl);
        //上传文件
        String filePath = fastDFSClient.uploadFile("c:\\1.png");
        System.out.println("返回路径：" + filePath);
        JTestLog.logJtest("http://120.77.172.83:12088/"+filePath);
    }
    @JTest
    @JTestClass.title(".test0002_fastdfs128")
    @JTestClass.pre("")
    @JTestClass.step("test0002_fastdfs128")
    @JTestClass.exp("ok")
    public void test0002_fastdfs128() throws Exception {
        String confUrl = PfApp.class.getResource("/fdfs_client128.properties").getPath();
        System.out.println(confUrl);
        FastDFSClient fastDFSClient = new FastDFSClient(confUrl);
        //上传文件
        String filePath = fastDFSClient.uploadFile("h:\\jpg\\2.jpg");
        System.out.println("返回路径：" + filePath);
        JTestLog.logJtest("http://192.168.222.128:12088/"+filePath);
    }

    @JTest
    @JTestClass.title(".test0003_fastdfs")
    @JTestClass.pre("")
    @JTestClass.step(" ")
    @JTestClass.exp("ok")
    public void test0003_fastdfs() throws Exception {
        String confUrl = PfApp.class.getResource("/fdfs_client63.properties").getPath();
        System.out.println(confUrl);
        FastDFSClient fastDFSClient = new FastDFSClient(confUrl);
        //上传文件
        String filePath = fastDFSClient.uploadFile("l:/3.png");
        System.out.println("返回路径：" + filePath);
        JTestLog.logJtest("http://120.78.136.63:8888/"+filePath);
    }
    //http://192.168.222.128:12088/group1/M00/00/00/wKjegF2q2tyAU648AAD6gBaK6NM8.4.jpg
    public static void main(String[] args) {
        run(TestFastDfs.class, 1);
    }
}
