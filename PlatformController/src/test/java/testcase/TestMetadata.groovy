package testcase

import com.alibaba.fastjson.JSONObject
import com.jtest.NodesFactroy.Inject.Inject
import com.jtest.NodesFactroy.Node.HttpClientNode
import com.jtest.annotation.JTest
import com.jtest.annotation.JTestClass
import com.jtest.testframe.ITestImpl
import com.jtest.utility.testlog.TestLog
import com.kunlong.platform.context.RestMessage.MsgRequest
import com.kunlong.platform.context.RestMessage.MsgResponse
import org.apache.curator.framework.CuratorFrameworkFactory
import testcase.pub.ManagerLogin

@JTestClass.author("leijm")
public class TestMetadata extends ITestImpl {
    MsgRequest req = new MsgRequest();
    String token = "f1fb2fca892d4e7aac0dd7d02d7d99e1";

    String url = "http://localhost:10080/rest/sysmetadata";

    @Inject(filename = "node.xml", value = "httpclient")
    HttpClientNode httpclient;

    ManagerLogin login = new ManagerLogin();
    String data;

    public void suiteSetUp() {
        req.token = token;
    }

    public void suiteTearDown() throws IOException {
    }

    @Override
    public void setUp() {
        token = login.login(req);
        req.token = token;

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.msgBody = JSONObject.parseObject("{ }");

    }

    @JTest
    @JTestClass.title(" test0001_insertMaster")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0001_insertMaster() {
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "dictByInsertSelective";
        String body = '''
        {"metadataName":"work_group_plan_landmark","metadataAlias":"work_group_plan_landmark","metadataMemo":"work_group_plan_landmark","metadataParentid":"","isCached":0,"metadataDb":"ytb_project","metadataAutocreate":1,"metadataStmt":"","metadataIndex":"","metadataOrder":"","metadataRemark":"","subsysId":"3","metadataType":6}'''

        body = """{"metadataName":"metadn${System.currentTimeMillis()}","metadataAlias":"dd","metadataMemo":"dd","metadataParentid":"","isCached":0,"metadataDb":"ytb_account","metadataAutocreate":1,"metadataStmt":"1","metadataIndex":"1","metadataOrder":"1","metadataRemark":"1","subsysId":"3","metadataType":1}"""
        req.msgBody = JSONObject.parseObject(body);

        data = JSONObject.toJSONString(req);
        TestLog.logJtest(data);
        String ret = httpclient.post("http://localhost:10080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);
    }

    @JTest
    @JTestClass.title(" test0002_getDictDataTypeList")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0002_getDictDataTypeList() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "getDictDataTypeList";
        String data = JSONObject.toJSONString(req);
        System.err.println(data);
        String ret = httpclient.post("http://localhost:10080/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);
    }

    //
    @JTest
    @JTestClass.title(" test0003_addDictDataType")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0003_addDictDataType() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "addDictDataType";
        data = JSONObject.toJSONString(req);
        System.err.println(data);
        String ret = httpclient.post("http://localhost/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);
    }

    @JTest
    @JTestClass.title(" test0004_addDictDataType")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0004_addDictDataType() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "getDictDataTypeById";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("dataInnerId",4)
        data = JSONObject.toJSONString(req);
        System.err.println(data);
        String ret = httpclient.post("http://localhost/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(data);
        System.out.println(resp);
    }

    @JTest
    @JTestClass.title(" test0005_deleteDictDataTypeById")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0005_deleteDictDataTypeById() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "deleteDictDataTypeById";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("dataInnerId",39)
        data = JSONObject.toJSONString(req);
        System.err.println(data);
        String ret = httpclient.post("http://localhost/rest/sysmetadata", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(data);
        System.out.println(resp);
    }


    @JTest
    @JTestClass.title(" test0006_getSubSysDictList")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0006_getSubSysDictList() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "getSubSysDictList";
        req.msgBody = JSONObject.parseObject("{}");

        String ret = httpclient.post(url, req.toJSONStringPretty(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);
    }

    @JTest
    @JTestClass.title(" test0007_getConfigCenter")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0007_getConfigCenter() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "configCenter";
        req.cmd = "getDictConfig";
        req.msgBody = JSONObject.parseObject("{}");
        data = JSONObject.toJSONString(req);
        System.err.println(data);
        String ret = httpclient.post("http://localhost/rest/configCenter", data, "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(data);
        System.out.println(resp);
    }

    @JTest
    @JTestClass.title(" test0008_getConfigCenter")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0008_getConfigCenter() {
        String url="http://localhost/rest/configCenter";
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "configCenter";
        req.cmd = "getDictErrorCode";
        req.msgBody = JSONObject.parseObject("{}");

        String ret = httpclient.post(url, req.toJSONStringPretty(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(data);
        System.out.println(resp);
    }


    @JTest
    @JTestClass.title(" test0009_createTableBymetadat")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0009_createTableBymetadat() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "makeTableByDictId";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("metadataId", 270);

        System.err.println(req.toJSONString());
        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(req.toJSONString());
        System.err.println(resp.toJSONString());
    }

    @JTest
    @JTestClass.title(" test0010_selectByTable")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0010_selectByTable() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "selectByTable";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("table", "ytb_project.electric_mat");

        System.err.println(req.toJSONString());
        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(req.toJSONStringPretty());
        //System.out.println(resp.toJSONStringPretty());
        System.err.println(url);
    }

    @JTest
    @JTestClass.title(" test0011_getDictTableAndField")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0011_getDictTableAndField() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "getDictTableAndField";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("metadataName", "electric_mat");

        System.err.println(req.toJSONString());
        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.err.println(url);
    }
    @JTest
    @JTestClass.title(" test0011_getDictTableAndField")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0012_checkMetadata() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "checkDict";
        req.msgBody = JSONObject.parseObject("{}");
        req.msgBody.put("metadataId", 55);

        System.err.println(req.toJSONString());
        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());
        System.err.println(url);
        //      List<DictDatatype> lst = SysCacheService.table2Cache("ytb_manager.dict_datatype", DictDatatype.class);


    }

    @JTest
    @JTestClass.title(" test0013_getCachedTableList")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0013_getCachedTableList() {

        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "getCachedTableList";
        req.msgBody = JSONObject.parseObject("{}");


        String ret = httpclient.post(url, req.toJSONString(), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.err.println(req.toJSONStringPretty());
        System.out.println(resp.toJSONStringPretty());


    }
    @JTest
    @JTestClass.title(" test0014_makeTableByDictId")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0014_makeTableByDictId() {
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "sysmetadata";
        req.cmd = "makeTableByDictId";
        req.msgBody = JSONObject.parseObject("{metadataId: 297}");

        String ret = httpclient.post("http://localhost:10080/rest/sysmetadata", JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);
    }

    @JTest
    @JTestClass.title(" test0014_makeTableByDictId")
    @JTestClass.pre("getDictTableAndField")
    @JTestClass.step("post http://mysql.kunlong.com:8080/rest/sysmetadata")
    @JTestClass.exp("ok")
    public void test0015_getSubSysDictList() {
        req.reqtime = System.currentTimeMillis();
        req.seqno = System.currentTimeMillis();
        req.cmdtype = "metadata";
        req.cmd = "getSubSysDictList";
        req.msgBody = JSONObject.parseObject("{metadataId: 297}");

        String ret = httpclient.post("http://localhost:10080/rest/sysmetadata", JSONObject.toJSONString(req), "application/json");
        httpclient.checkStatusCode(200);

        MsgResponse resp = MsgResponse.parseResponse(ret);
        checkEQ(0, resp.getRetcode());
        System.out.println(resp);
    }


    public static void main(String[] args) {
        CuratorFrameworkFactory l;
        run(TestMetadata.class, 15);
    }
}
