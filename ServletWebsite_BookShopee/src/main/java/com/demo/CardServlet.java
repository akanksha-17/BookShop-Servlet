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
import java.sql.ResultSet;


public class CardServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		PrintWriter out = response.getWriter();
		int i = Integer.parseInt(request.getParameter("Bid"));
		System.out.println("Book ID - "+i);
		//String u = request.getParameter("userid");
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshopee","root","root");	
			PreparedStatement pstmt1=con.prepareStatement("select * from enggBook where bookid=?");
			pstmt1.setInt(1, i);
            ResultSet rs=pstmt1.executeQuery();
           // int bid, br, by;
           // String bn,ba,bp;
            out.println("<html lang=\"en\">");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">"); 
            out.println("<body>"); 
            out.println("<table class=table table-primary>"); 
            if(rs.next())
            { 
               out.println("<tr>"); 
               out.println("<td>"+ rs.getInt(1)+ "</td>"); 
               out.println("<td>"+ rs.getString(2)+"</td>"); 
               out.println("<td>"+rs.getString(3)+"</td>"); 
               out.println("<td>"+rs.getString(4)+"</td>"); 
               out.println("<td>"+rs.getInt(5)+"</td>"); 
               out.println("<td>"+rs.getInt(6)+"<td>"); 
               out.println("</tr>");
            }
            out.println("</table>");                  
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		 out.print("Book details of book id " + i);
		
		
		/*
		 * out.println(n);
		   out.println(r);
		   */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
