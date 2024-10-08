package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HistoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 	{
		
		 PrintWriter out= response.getWriter();	
	     out.println("<html lang=\"en\">"); 
	     out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
	     out.println("<body>");
	     out.println("<h1>Historical Books</h1>\r\n");
	     out.println("<div class=\"container\">");
	     out.println("<div class=row>");
	     /* For the connection with JDBC */
	     try
	     {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshopee", "root","root");
			PreparedStatement pstmt=con.prepareStatement("select * from histoBook");
			ResultSet rs=pstmt.executeQuery();
			int ID, Year, Price;
			String Name, Author, Publication, Image;
			while(rs.next())
			{
			  ID=rs.getInt(1);
			  Name=rs.getNString(2);
			  Author=rs.getNString(3);
			  Publication=rs.getString(4);
			  Year=rs.getInt(5);
			  Price=rs.getInt(6);
			  Image=rs.getString(7);
			  
			 /* out.println("<div class=col"); 
			   out.println("<div>"+ID+"</div>"); 
			   out.println("<div>"+Name+"</div>"); 
			   out.println("<div>"+Author+"</div>"); 
			   out.println("<div>"+Publication+"</div>"); 
			   out.println("<div>"+Year+"</div>"); 
			   out.println("<div>"+Price+"</div>"); 
			   out.println("<div>"+Image+"</div>"); 
			   out.println("</div>"); 
			   */
			   out.println("<div class=col-4>");
			   out.println("<div class=card style=width: 18rem;>");
			   out.println("<img height=300px src='"+Image+"'class=\"card-img-top\" alt=\"...\">");
			   out.println("<div class=\"card-body\">");
			   out.println("<h3 class=card-title>"+Name+"</h3>");
			   out.println("<p class=\"card-text\">"+Author+"</p>");
			   out.println("<p class=\"card-text\">"+Publication+"</p>");
			   out.println("<p class=\"card-text\">"+Year+"</p>");
			   out.println("<p class=\"card-text\">"+Price+"</p>");
			   out.println("<a href=\"#\" class=\"btn btn-primary\">Add to card </a>\r\n");
			   out.println("</div>");
			   out.println("</div>");
			   out.println("</div>");
			   
			}
		 } catch (Exception e) 
	     {
            System.out.println(e);
        }
	     out.println("</div>");
	     out.println("</div>");
	     out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script> \r\n");
	     out.println("</body>");  
	     out.println("</html>");
	     

		

	}

 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
 	{
		doGet(request, response);
	}

}
