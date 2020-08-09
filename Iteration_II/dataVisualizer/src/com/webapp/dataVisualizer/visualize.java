package com.webapp.dataVisualizer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Object;


@WebServlet("/visualize")
//visualize is the controller (servlet) 
public class visualize extends HttpServlet {
	String db_name; 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db_name = (String)request.getParameter("dataset");
		System.out.println(db_name);
		//get data from the database
		List<Dataset> values;
		
		try {
			System.out.println(db_name);
			values = visualizeDbUtil.getDataList(db_name);
			request.setAttribute("values", values);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//redirect to a different page (view - JSP)
		RequestDispatcher dispatcher = request.getRequestDispatcher("show-data.jsp");
		dispatcher.forward(request, response);
	}
}