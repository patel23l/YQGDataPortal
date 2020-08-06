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

@WebServlet("/visualize")
//visualize is the controller (servlet) 
public class visualize extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get data from the database
		List<Dataset> values;
		try {
			values = visualizeDbUtil.getDataList();
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