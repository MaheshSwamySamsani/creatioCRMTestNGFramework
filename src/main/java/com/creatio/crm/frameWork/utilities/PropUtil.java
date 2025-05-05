package com.creatio.crm.frameWork.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtil {
	
	public static Properties readData(String fileName) {
		//Initialize the Properties class
		Properties prop = new Properties();		
	
		try {
			//Read the properties file
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Config\\"+fileName);
			
			//copy Properties into prop variable
			prop.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;		
	}
	

}
