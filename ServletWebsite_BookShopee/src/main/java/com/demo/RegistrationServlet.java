package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			String n=request.getParameter("txtname");
			String a=request.getParameter("txtaddress");
			String c=request.getParameter("txtcity");
			String e=request.getParameter("txtemail");
			String u=request.getParameter("txtusername");
			String p=request.getParameter("txtpassword");
			
			//JDBC
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshopee","root","root");
				PreparedStatement ps=con.prepareStatement("insert into users(name,address,city,email,username,password) values(?,?,?,?,?,?)");
				ps.setString(1, n);
				ps.setString(2, a);
				ps.setString(3, c);
				ps.setString(4, e);
				ps.setString(5, u);
				ps.setString(6, p);
				
				ps.executeUpdate();
				PrintWriter out=response.getWriter();
				out.println("User Registered successfully");
			} catch (ClassNotFoundException e1) 
			{
				e1.printStackTrace();
			} catch (SQLException e1)
			{
				System.out.println(e1);
				e1.printStackTrace();
			}
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			doGet(request, response);
		}
}
