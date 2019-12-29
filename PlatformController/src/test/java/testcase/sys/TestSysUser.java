package testcase.sys;


import cn.integriti.center.api.dto.queryParam.SysUserQueryDTO;
import com.alibaba.fastjson.JSON;
import com.jtest.NodesFactroy.Inject.Inject;
import com.jtest.NodesFactroy.Node.HttpClientNode;
import com.jtest.annotation.JTest;
import com.jtest.annotation.JTestClass;
import com.jtest.testframe.ITestImpl;
import com.kunlong.api.dto.queryParam.MetadataDictModelQueryDTO;
import com.kunlong.api.model.MetadataDictModelDTO;
import com.kunlong.api.model.MetadataFieldModelDTO;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.context.RestMessage.MsgRequest;
import com.kunlong.platform.support.service.AuthService;
import com.kunlong.platform.utils.KunlongUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testcase.pub.ManagerLogin;

import java.io.IOException;
import java.util.List;

@JTestClass.author("leijm")
public class TestSysUser extends ITestImpl {
	private static final Logger logger = LoggerFactory.getLogger(TestSysUser.class);

	String url_login = "http://127.0.0.1:10080/auth/login?username=admin&password=123456";
	String url_auth = "http://127.0.0.1:10080/sys/user/authorization";

	String url_sysuser = "http://127.0.0.1:10080/sys/user";
	String url_metadata = "http://127.0.0.1:10080/sys/metadata";

	@Inject(filename = "node.xml", value = "httpclient")
	HttpClientNode httpclient;
	//http://localhost:8089/api/auth/login?username=admin&password=111111

	ManagerLogin login = new ManagerLogin();
	String token = "d98c3b969dc34aaa92942c8a9c646f2a";
	int apiKey = 1111;


	MsgRequest req = new MsgRequest();

	public void suiteSetUp() {
	
	}
	
	public void suiteTearDown() throws IOException {
	}

	AuthService.AuthToken authToken;

	public AuthService.AuthToken loginsys() {


		String ret = httpclient.post(url_login, "{}", "application/json");
		authToken = KunlongUtils.parseObject(ret, AuthService.AuthToken .class);
		System.out.println(KunlongUtils.toJSONStringPretty(authToken));
		httpclient.checkStatusCode(200);
		return authToken;

	}

	void auth() {

		httpclient.addHeader(ApiConstants.AUTH_TOKEN_KEY_WEB, authToken.getToken());
		//httpclient.addHeader( "access-token",authToken.getToken());
		String ret = httpclient.post(url_auth, "{}", "application/json");

	}

	@Override
	public void setUp() {
		 authToken=loginsys();
		 auth();		//req = login.defaultReq();		//token = login.login(req);
	}
	
	@Override
	public void tearDown() {

	}

	@JTest
	@JTestClass.title("test_001_querySysUser")
	@JTestClass.pre("")
	@JTestClass.step("url_context")
	@JTestClass.exp("ok")
	public void test_001_querySysUser() {

		SysUserQueryDTO sysUserQueryDTO = new SysUserQueryDTO();

		httpclient.addHeader(ApiConstants.AUTH_TOKEN_KEY_WEB, authToken.getToken());
		String ret = httpclient.post(url_sysuser + "/query", KunlongUtils.toJSONStringPretty(sysUserQueryDTO), "application/json");

		httpclient.checkStatusCode(200);


	}


	@JTest
	@JTestClass.title("获取用户列表")
	@JTestClass.pre("")
	@JTestClass.step("test_002_queryFields")
	@JTestClass.exp("ok")
	public void test_002_queryFields() {

		String ret = httpclient.post(url_metadata+"/queryFields", "{}", "application/json");
		httpclient.checkStatusCode(200);
		System.out.println(ret);
		List<MetadataFieldModelDTO> list= JSON.parseObject(ret,List.class);
		System.out.println(KunlongUtils.toJSONStringPretty(list));
		System.out.println(list.size());


	}
	@JTest
	@JTestClass.title("获取用户列表")
	@JTestClass.pre("")
	@JTestClass.step("test_003_queryFieldsByTable")
	@JTestClass.exp("ok")
	public void test_003_queryFieldsByTable() {

		String ret = httpclient.post(url_metadata
				+ "/queryFieldsByTable?tableName=dict_area", "{}", "application/json");
		httpclient.checkStatusCode(200);
		System.out.println(ret);
		List<MetadataFieldModelDTO> list = JSON.parseObject(ret, List.class);
		System.out.println(KunlongUtils.toJSONStringPretty(list));
		System.out.println(list.size());


	}

	@JTest
	@JTestClass.title("获取用户列表")
	@JTestClass.pre("")
	@JTestClass.step("test_002_queryFields")
	@JTestClass.exp("ok")
	public void test_004_queryTbls() {
		MetadataDictModelQueryDTO queryDTO=new MetadataDictModelQueryDTO();
		queryDTO.setParam(new MetadataDictModelDTO());
		queryDTO.getParam().setMetadataId(59);
		queryDTO.getParam().setMetadataName("account_user_out");
		String ret = httpclient.post(url_metadata+"/queryDicts", KunlongUtils.toJSONString(queryDTO), "application/json");
		httpclient.checkStatusCode(200);
		System.out.println(ret);
		List<MetadataDictModelDTO> list= JSON.parseObject(ret,List.class);
		System.out.println(KunlongUtils.toJSONStringPretty(list));
		System.out.println(list.size());


	}
	public static void main(String[] args) {

		 run(TestSysUser.class, 3);

	}


}
		