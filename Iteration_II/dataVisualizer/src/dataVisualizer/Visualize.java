package dataVisualizer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Visualize")
public class Visualize extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//this is a controller servlet that talks to the model, gets data, process it, sends it to view
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the data from a dataset (model)
		String[] votes = {"24", "2", "9", "10", "1"};
		request.setAttribute("votes", votes);
		
		//redirect to a different page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("show-data.jsp");
		dispatcher.forward(request, response);
	}
}
