package com.webapp.dataVisualizer;

import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class visualizeDbUtil {
	public static List<Dataset> getDataList(String name) throws ClassNotFoundException, SQLException {

		String db = "datasets";
		String table = name;
				
		ArrayList<Dataset> data = new ArrayList<>();
		ArrayList<String> fields = new ArrayList<>();
		
		try {
			String url = "jdbc:mysql://localhost:3306/" + db;
			String user = "root";
			String pass = "JatiRehje@2020";
			
			//take a csv file and automatically add that to the database!
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			
			//get the number of fields of a given dataset
			ResultSet rs = stmt.executeQuery("select * from " + table);
			ResultSetMetaData rsmd = (com.mysql.cj.jdbc.result.ResultSetMetaData) rs.getMetaData();
			int col = rsmd.getColumnCount();
								
			//get all the fields into an arraylist
			rs = stmt.executeQuery("describe " + table);
			while (rs.next()) {
				String field = rs.getString(1);
				fields.add(field);
			}
			
			//==========================================================
			rs = stmt.executeQuery("select * from " + table);
			
			while (rs.next()) {
				String temp = "";

				for (int i = 1; i <= col; i++) {
					temp += rs.getString(i);
					temp += " ";
				}
				Dataset t = new Dataset(temp);
				data.add(t);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
}
