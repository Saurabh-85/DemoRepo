package com.qa.client;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	//1. Get Method
	
	public CloseableHttpResponse get(String url)throws ClientProtocolException, IOException{
		
		//1. Get method without headers
		CloseableHttpClient client= HttpClients.createDefault();
		HttpGet httpget =  new HttpGet(url);
		CloseableHttpResponse response = client.execute(httpget);//hitting the get URL
		
		return response;		
	}
	public CloseableHttpResponse get(String url,HashMap<String, String> headermap)throws ClientProtocolException, IOException{
		
		//1. Get method with headers
		CloseableHttpClient client= HttpClients.createDefault();
		HttpGet httpget =  new HttpGet(url);
		
		for(Map.Entry<String, String> entry : headermap.entrySet()){
			
			httpget.addHeader(entry.getKey(),entry.getValue());
			
			
			
			
		}
			
		
		
		
		CloseableHttpResponse response = client.execute(httpget);//hitting the get URL
		
		return response;		
	}
	
	
	//post method
	public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headermap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient client= HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString));
		
		//for headers
		for(Map.Entry<String, String> entry : headermap.entrySet())
			httppost.addHeader(entry.getKey(), entry.getValue());	
			
			CloseableHttpResponse response = client.execute(httppost);
			return response;
			
			
		
		
		
		
	}

}
