package com.wp.Books;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartManager
 */
@WebServlet("/CartManager")
public class CartManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		HttpSession session=request.getSession();
		String id = (String) session.getAttribute("user");
		String username=(String)session.getAttribute("uName");

					if (id == null)
					{
						response.sendRedirect("index.jsp");
					}
		String code=request.getParameter("code");
        
       // HttpSession session=request.getSession();
        
        HashSet<String> set=(HashSet<String>) session.getAttribute("cart");
        
        if(set==null){
            set=new HashSet<String>();
        }
        
        set.add(code);
        
        session.setAttribute("cart", set);
        response.sendRedirect("SubjectPageServlet");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
