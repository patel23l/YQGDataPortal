package com.webapp.dataVisualizer;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class visualizeDbUtil {
	public static List<Dataset> getDataList() throws ClassNotFoundException, SQLException {
				
		ArrayList<Dataset> data = new ArrayList<>();

		//1. get a connection to database
		Connection myConn;
		try {
			String url = "jdbc:mysql://localhost:3306/datasets";
			String user = "root";
			String pass = "JatiRehje@2020";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from election");
			
			while (rs.next()) {
				int ward = rs.getInt(1);
				String name = rs.getString(2);
				int votes = rs.getInt(3);
				
				Dataset d = new Dataset(ward, name, votes);
				data.add(d);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
}
