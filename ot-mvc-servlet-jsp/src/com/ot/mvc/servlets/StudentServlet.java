package com.ot.mvc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ot.mvc.services.StudentService;

@WebServlet( name = "StudentServlet", urlPatterns = "/student-record")
public class StudentServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5166570066172359898L;
	
	private StudentService studentService = new StudentService();
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String studentID = request.getParameter("id");
		
		if (studentID != null) {
			int id = Integer.parseInt(studentID);
			
			studentService.getStudent(id).ifPresent(s -> request.setAttribute("studentRecord",  s));
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/student-record.jsp");
		
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
