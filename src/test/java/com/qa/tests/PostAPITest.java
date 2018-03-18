package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.BaseTest;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends BaseTest {

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
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		
		//jackson API
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("saurabh3","test3");//expected users object
		
		//object to json file: 
		mapper.writeValue(new File("C:\\Users\\sdwivedi-a\\self\\_API_Naveen\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//object to json in String:
		String usersjsonstring = mapper.writeValueAsString(users);
		System.out.println(usersjsonstring);
		
		response=restclient.post(url, usersjsonstring, headermap);
		//status code
		int statuscode = response.getStatusLine().getStatusCode();
		System.out.println("Post Staus code->"+statuscode);
		Assert.assertEquals(statuscode, BaseTest.RESPONSE_STATUS_CODE_201);
		
		//jsonString
		String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
		System.out.println("responseString-->"+responseString);
		
		JSONObject responseJson =  new JSONObject(responseString);
		System.out.println("The response fron API is :- "+responseJson);
		
		//json to java object(marshelling)
		Users userobj = mapper.readValue(responseString, Users.class);
		System.out.println("users response"+userobj);
		
		//print
		System.out.println(users.getName().equals(userobj.getName()));
		System.out.println(users.getJob().equals(userobj.getJob()));
		
		Assert.assertTrue(users.getName().equals(userobj.getName()));
		Assert.assertTrue(users.getJob().equals(userobj.getJob()));
		
		
		
		System.out.println("ID is - "+userobj.getId());
		System.out.println("Created at - "+userobj.getCreatedAt());
		
	}
	
	
	
	
}
