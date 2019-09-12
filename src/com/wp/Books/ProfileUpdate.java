package com.wp.Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProfileUpdate
 */
@WebServlet("/ProfileUpdate")
public class ProfileUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String dbusername = context.getInitParameter("dbusername");
		String dbpassword = context.getInitParameter("dbpassword");
		
		String password=request.getParameter("password");
		String username=request.getParameter("username");
		String address=request.getParameter("address");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		
		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("user");
	

					if (id == null)
					{
						response.sendRedirect("index.jsp");
					}
		
		PrintWriter out=response.getWriter();
		try {
			
		
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,dbusername,dbpassword);
		String sql="update users set uname=?,password=?,address=?,mobile=?,emailid=? where userid=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,username);
		ps.setString(2,password);
		ps.setString(3,address);
		ps.setString(4,mobile);
		ps.setString(5,email);
		ps.setString(6,id);
		ps.executeUpdate();
		out.println("<html><body>");
		out.println("<center><h3> Profile Updated</h3></center>");
		out.println("<hr>");
		out.println("<a href=buyerpage.jsp>Buyer-Page</a>");
		out.println("</html></body>");
		con.close();
		
		
		out.println("<hr>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
