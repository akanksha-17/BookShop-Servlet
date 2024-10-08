package com.demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String n=request.getParameter("txtusername");
		String p=request.getParameter("txtpassword");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshopee","root","root");
			PreparedStatement ps=con.prepareStatement("select username,password from users where username=? and password =?");
			ps.setString(1, n);
			ps.setString(2, p);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) 
			{
				RequestDispatcher rd=request.getRequestDispatcher("CardServlet");
				rd.forward(request, response);
			}else
			{
				out.println("<h2>Invalid username or password. Please try again</h2>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
				/*
				out.println("Wrong username or password");
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
				*/
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
