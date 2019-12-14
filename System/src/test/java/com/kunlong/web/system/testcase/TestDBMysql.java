package com.kunlong.web.system.testcase;

import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.JDbNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import java.util.*;


@JTestClass.author("leijm")
public class TestDBMysql extends ITestImpl {


    @Inject("system")
    JDbNode dbSystem;



    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @JTest
    @JTestClass.title("test0001_select")
    @JTestClass.pre("")
    @JTestClass.step("post http://mysql.kunlong.com:80/sysuser/tagTableService/manager")
    @JTestClass.exp("ok")
    public void test0001_select() throws Exception {

        List<Map<String, Object>> result = dbSystem.clearSql().appendSql("select * from dict_datatype").select();
        System.out.println(result);
    }



    public static void main(String[] args) throws Exception {

         run(TestDBMysql.class, 1);

    }


}


