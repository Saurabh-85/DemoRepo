package com.qa.tests;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends BaseTest {
	BaseTest testbase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restclient;
	CloseableHttpResponse response;
@BeforeMethod
public void setup() throws ClientProtocolException, IOException{
	testbase=new BaseTest();
	String serviceurl = prop.getProperty("url");	
	String apiurl=prop.getProperty("serviceurl");
	
	url = serviceurl+apiurl;

	
	
}

@Test(priority=1)
public void getAPITestWithoutHeader() throws ClientProtocolException, IOException{
	System.out.println("getTest->"+url);
	restclient = new RestClient();
	response = restclient.get(url);
	
			//a.Status Code :
			int statuscode = response.getStatusLine().getStatusCode();
			System.out.println("Status Code -->"+statuscode);
			
			Assert.assertEquals(statuscode, BaseTest.RESPONSE_STATUS_CODE_200,"Status code is not 200");
			
			
			//b. JSon String
			String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
			System.out.println(responseString);
			
			JSONObject responsejson = new JSONObject(responseString);
			System.out.println("Response JSON:-"+responsejson);
			
			//Single value assertion
			String perPageValue = TestUtil.getValueByJpath(responsejson, "/per_page");
			String total = TestUtil.getValueByJpath(responsejson, "total");
			System.out.println("responsejson, \"/per_page\"---->"+perPageValue);
			System.out.println("responsejson, \"/total\"---->"+total);
			
			Assert.assertEquals(Integer.parseInt(perPageValue),3);
			Assert.assertEquals(Integer.parseInt(total), 12);
			
			//get the value from the JSON Array:
			String last_name = TestUtil.getValueByJpath(responsejson, "/data[0]/last_name");
			String id = TestUtil.getValueByJpath(responsejson, "/data[0]/id");
			String avatar = TestUtil.getValueByJpath(responsejson, "/data[0]/avatar");
			String first_name = TestUtil.getValueByJpath(responsejson, "/data[0]/first_name");
			
			System.out.println(last_name);
			System.out.println(id);
			System.out.println(avatar);
			System.out.println(first_name);
			
			
			
			//c.All headers
			Header[] headerarray = response.getAllHeaders();
			
			HashMap<String,String> allheader = new HashMap<String, String>();
			
			for(Header header : headerarray){
				allheader.put(header.getName(), header.getValue());
			}
			System.out.println("Header Array-->"+allheader);
			

	
	
	
}

@Test(priority=2)
public void getAPITestWithHeader() throws ClientProtocolException, IOException{
	System.out.println("getTest->"+url);
	restclient = new RestClient();
	
	HashMap<String, String> headermap = new HashMap<String, String>();
	headermap.put("Content-Type", "application/json");
	//headermap.put("username", "test");
	//headermap.put("password", "test");
	//headermap.put("Auth Token", "12345");
	
	
	response = restclient.get(url,headermap);
	
	
			//a.Status Code :
			int statuscode = response.getStatusLine().getStatusCode();
			System.out.println("Status Code -->"+statuscode);
			
			Assert.assertEquals(statuscode, BaseTest.RESPONSE_STATUS_CODE_200,"Status code is not 200");
			
			
			//b. JSon String
			String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
			System.out.println(responseString);
			
			JSONObject responsejson = new JSONObject(responseString);
			System.out.println("Response JSON:-"+responsejson);
			
			//Single value assertion
			String perPageValue = TestUtil.getValueByJpath(responsejson, "/per_page");
			String total = TestUtil.getValueByJpath(responsejson, "total");
			System.out.println("responsejson, \"/per_page\"---->"+perPageValue);
			System.out.println("responsejson, \"/total\"---->"+total);
			
			Assert.assertEquals(Integer.parseInt(perPageValue),3);
			Assert.assertEquals(Integer.parseInt(total), 12);
			
			//get the value from the JSON Array:
			String last_name = TestUtil.getValueByJpath(responsejson, "/data[0]/last_name");
			String id = TestUtil.getValueByJpath(responsejson, "/data[0]/id");
			String avatar = TestUtil.getValueByJpath(responsejson, "/data[0]/avatar");
			String first_name = TestUtil.getValueByJpath(responsejson, "/data[0]/first_name");
			
			System.out.println(last_name);
			System.out.println(id);
			System.out.println(avatar);
			System.out.println(first_name);
			
			
			
			//c.All headers
			Header[] headerarray = response.getAllHeaders();
			
			HashMap<String,String> allheader = new HashMap<String, String>();
			
			for(Header header : headerarray){
				allheader.put(header.getName(), header.getValue());
			}
			System.out.println("Header Array-->"+allheader);
			

	
	
	
}


}
