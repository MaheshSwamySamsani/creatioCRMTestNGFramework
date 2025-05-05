package com.creatio.crm.frameWork.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {
	
	public static ResultSet getData(String query) throws SQLException {
		ResultSet dataSet = null;			
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdshop", "postgres", "admin");
		dataSet = connection.createStatement().executeQuery(query);		
		return dataSet;		
	}
	
	public static List<Map<String,String>> readData(String query) throws SQLException{
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		ResultSet rawData = getData(query);
		
		while(rawData.next()) {
			Map<String,String> rowData = new HashMap<String,String>();
			for(int col=1; col<=rawData.getMetaData().getColumnCount() ; col++) {
				String columnName=rawData.getMetaData().getColumnName(col);
				String columnValue=rawData.getString(col);
				rowData.put(columnName, columnValue);
			}
			data.add(rowData);
		}		
		return data;		
	}

}
