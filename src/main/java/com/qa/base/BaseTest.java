package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class BaseTest {
	
	public static final int RESPONSE_STATUS_CODE_200=200	;
	public static final int RESPONSE_STATUS_CODE_500=500;
	public static final int RESPONSE_STATUS_CODE_400=400;
	public static final int RESPONSE_STATUS_CODE_201=201;
	public static final int RESPONSE_STATUS_CODE_401=401;
	
	public Properties prop;	
	
	public BaseTest(){
		
		prop = new Properties();
		try {
			//System.out.println("system directory is- "+ System.getProperty("user.dir"));
			//C:\Users\sdwivedi-a\self\_API_Naveen\src\main\java\com\qa\config\config.properties
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
			//System.out.println(prop.getProperty("url")+prop.getProperty("serviceurl"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
				
		
		
		
	}

}
